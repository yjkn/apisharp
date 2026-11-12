package com.apisharp.core.documentation;

import com.apisharp.core.model.ApiDefinition;
import com.apisharp.core.model.ApiParameter;
import com.apisharp.core.model.ApiResponse;

public final class MarkdownDocumentGenerator {
    public String generate(ApiDefinition apiDefinition) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("# ").append(apiDefinition.name()).append('\n').append('\n');
        markdown.append("- Method: `").append(apiDefinition.method()).append("`\n");
        markdown.append("- Path: `").append(apiDefinition.path()).append("`\n");
        markdown.append("- Project: `").append(apiDefinition.projectKey()).append("`\n\n");
        if (apiDefinition.description() != null && !apiDefinition.description().isBlank()) {
            markdown.append(apiDefinition.description()).append("\n\n");
        }
        markdown.append("## Parameters\n\n");
        if (apiDefinition.parameters().isEmpty()) {
            markdown.append("No parameters defined.\n\n");
        } else {
            markdown.append("| Name | Location | Type | Required | Description |\n");
            markdown.append("| --- | --- | --- | --- | --- |\n");
            for (ApiParameter parameter : apiDefinition.parameters()) {
                markdown.append("| ").append(parameter.name())
                        .append(" | ").append(parameter.location())
                        .append(" | ").append(parameter.type())
                        .append(" | ").append(parameter.required())
                        .append(" | ").append(parameter.description() == null ? "" : parameter.description())
                        .append(" |\n");
            }
            markdown.append('\n');
        }
        markdown.append("## Responses\n\n");
        for (ApiResponse response : apiDefinition.responses()) {
            markdown.append("- `").append(response.status()).append("` ")
                    .append(response.description() == null ? "" : response.description())
                    .append('\n');
        }
        return markdown.toString();
    }
}
