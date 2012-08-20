import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.goeswhere.tinyjar.Packager;

/**
 * Package the jar with tinyjar
 * @goal package
 * @requiresProject true
 */
public class TinyjarMojo extends AbstractMojo {
	
	/** @parameter default-value="${project}" */
	private MavenProject project;
		
	public void execute() throws MojoExecutionException, MojoFailureException {

		// Redirect stderr, which tinyjar logs with, to maven's logger
		System.setErr( new PrintStream( new ByteArrayOutputStream(){

			private StringBuilder record = new StringBuilder();
			
			public void flush() throws IOException { 
				String current;
				synchronized(this) { 
					super.flush(); 
					current = this.toString();
					super.reset(); 
					if (current.length() == 0 || current.equals(System.getProperty("line.separator"))) {
						if (record.toString().length() != 0) {		            	
							getLog().info(record.toString().replaceAll("\n", ""));
							record.setLength(0);		            	
						} else {
							record.append(current);
						}
					} 
				} 
			}
		} ,true));
		
		//Look in build directory for jars
		final File buildDir = new File(project.getBuild().getDirectory());
		log("Examining %s for .jar files",buildDir.getPath());
		
		//Process
		for (final File file : buildDir.listFiles()) {
			if (file.getName().endsWith(".jar"))
				tinyJar(file);
		}
		
		getLog().info("Done.");
	
	}

	private void tinyJar(final File file) {
		
		log("Generating tinyjar for %s...",file.getName());
		
		try {
			Packager.main(new String[]{ file.getAbsolutePath() });
		} catch (IOException e) {
			log("Unable to create tinyjar for %s", file.getName());
		}
	}
		
	private void log(String format, String string) {
		getLog().info(String.format(format, string));
	}
	
}
