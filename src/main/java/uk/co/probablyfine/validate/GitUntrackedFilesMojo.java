package uk.co.probablyfine.validate;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;

/**
 * @goal untracked-files
 * @requiresProject true
 */
public class GitUntrackedFilesMojo extends AbstractValidationMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        final Status call;

        try {
            call = new Git(getRepo()).status().call();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        
        if(call.getUntracked().size() > 0) {
            throw new MojoExecutionException("Untracked files that have no been committed.");
        }
    }
	
}
