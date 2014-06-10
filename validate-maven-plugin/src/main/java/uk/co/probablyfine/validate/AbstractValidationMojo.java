package uk.co.probablyfine.validate;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public abstract class AbstractValidationMojo extends AbstractMojo {

    /** @parameter default-value="${project}" */
    private MavenProject project;

    protected Repository getRepo() throws MojoExecutionException {

        final FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder()
            .findGitDir(project.getBasedir());

        Repository repo;

        try {
            repo = repoBuilder.build();
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage());
        }

        return repo;

    }

}
