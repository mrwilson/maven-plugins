package uk.co.probablyfine.validate;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

/**
 * @goal diff-upstream
 * @requiresProject true
 * @phase verify
 */
public class GitDiffAgainstUpstreamMojo extends AbstractValidationMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        final List<DiffEntry> diffs;

        try {
            final Repository repo = getRepo();
            final Git git = new Git(repo);

            final ObjectId upstream  = repo.resolve("@{upstream}^{tree}");
            final ObjectId head      = repo.resolve("HEAD^{tree}");

            final ObjectReader reader = repo.newObjectReader();

            final CanonicalTreeParser upstreamIter = new CanonicalTreeParser();
            upstreamIter.reset(reader, upstream);

            final CanonicalTreeParser headIter = new CanonicalTreeParser();
            headIter.reset(reader, head);

            diffs = git
                    .diff()
                    .setOldTree(upstreamIter)
                    .setNewTree(headIter)
                    .call();

        } catch (Exception e){
            throw new RuntimeException(e);
        }

        if(diffs.size() > 0) {
            throw new MojoExecutionException("Local repository is ahead of upstream, push first.");
        }
    }
}
