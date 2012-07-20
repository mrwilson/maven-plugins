package uk.co.probablyfine.util;
import java.util.Collection;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public abstract class AbstractSearchMojo extends AbstractMojo {

	/**
     * @parameter expression="${query}" default-value=" "
     */
	protected String query;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		getLog().info("Attempting search...");
		
		if (this.query.equals(" ")) 
			getLog().info("No query given, specify query with -Dquery=<querystring>");
		else 
			prettyPrint(executeQuery());
		
	}

	private void prettyPrint(final Collection<String> results) {
		
		if (results.isEmpty())
			getLog().info("No results found for \""+query+"\"");
		else {
			getLog().info("Results for \""+query+"\":");
		
			for (final String string : results) {
				getLog().info("\t"+string);
			}
		}
	}

	public abstract Collection<String> executeQuery();
	
}
