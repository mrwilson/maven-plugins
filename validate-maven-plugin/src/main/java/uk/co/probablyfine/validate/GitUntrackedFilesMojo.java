package uk.co.probablyfine.validate;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import java.util.Arrays;

/**
 * @goal untracked-files
 * @requiresProject true
 * @phase verify
 */
public class GitUntrackedFilesMojo extends AbstractValidationMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        final Status status;

        try {
            status = new Git(getRepo()).status().call();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        int untracked = status.getUntracked().size();
        int changed   = status.getChanged().size();
        int modified  = status.getModified().size();

        if(untracked > 0) {
            throw new MojoExecutionException("Untracked files that have not been committed. Files untracked: "+ Arrays.toString(status.getUntracked().toArray()));
        }

        if(changed > 0) {
            throw new MojoExecutionException("Changed files that have not been committed. Files changed: "+ Arrays.toString(status.getChanged().toArray()));
        }

        if(modified > 0) {
            throw new MojoExecutionException("Modified files that have not been committed. Files modified: "+ Arrays.toString(status.getModified().toArray()));
        }

    }

}