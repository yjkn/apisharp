package com.apisharp.plugin;

import com.apisharp.core.compliance.ComplianceEngine;
import com.apisharp.core.compliance.ComplianceFinding;
import com.apisharp.core.documentation.MarkdownDocumentGenerator;
import com.apisharp.core.model.ApiDefinition;
import com.apisharp.core.model.ApiResponse;
import com.apisharp.core.model.HttpMethod;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Mojo(name = "review", defaultPhase = LifecyclePhase.VERIFY, threadSafe = true)
public class ApiSharpMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.artifactId}", readonly = true)
    private String projectKey;

    @Parameter(defaultValue = "${project.build.directory}/apisharp/api.md")
    private java.io.File outputFile;

    @Parameter(property = "apisharp.failOnError", defaultValue = "true")
    private boolean failOnError;

    @Override
    public void execute() throws MojoExecutionException {
        ApiDefinition apiDefinition = new ApiDefinition(
                projectKey,
                "Sample API discovered by APISharp plugin",
                HttpMethod.GET,
                "/sample-api",
                "Replace this generated example with scanner output when controller scanning is enabled.",
                List.of(),
                List.of(new ApiResponse(200, "application/json", "{}", "Successful response"))
        );

        List<ComplianceFinding> findings = ComplianceEngine.defaultEngine().review(apiDefinition);
        findings.forEach(finding -> getLog().warn(finding.ruleCode() + ": " + finding.message()));
        if (failOnError && findings.stream().anyMatch(finding -> finding.severity() == ComplianceFinding.Severity.ERROR)) {
            throw new MojoExecutionException("APISharp compliance review failed.");
        }
        writeDocument(apiDefinition);
    }

    private void writeDocument(ApiDefinition apiDefinition) throws MojoExecutionException {
        try {
            Files.createDirectories(outputFile.toPath().getParent());
            Files.writeString(outputFile.toPath(), new MarkdownDocumentGenerator().generate(apiDefinition), StandardCharsets.UTF_8);
            getLog().info("APISharp document generated at " + outputFile);
        } catch (IOException exception) {
            throw new MojoExecutionException("Unable to write APISharp document.", exception);
        }
    }
}
