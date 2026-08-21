package com.apisharp.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record TestStep(
        @NotBlank String name,
        @NotNull HttpMethod method,
        @NotBlank String url,
        Map<String, String> headers,
        String body,
        int expectedStatus
) {
    public TestStep {
        headers = headers == null ? Map.of() : Map.copyOf(headers);
    }
}
