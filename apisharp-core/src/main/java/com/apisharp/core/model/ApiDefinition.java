package com.apisharp.core.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ApiDefinition(
        @NotBlank String projectKey,
        @NotBlank String name,
        @NotNull HttpMethod method,
        @NotBlank String path,
        String description,
        @Valid List<ApiParameter> parameters,
        @Valid List<ApiResponse> responses
) {
    public ApiDefinition {
        parameters = parameters == null ? List.of() : List.copyOf(parameters);
        responses = responses == null ? List.of() : List.copyOf(responses);
    }
}
