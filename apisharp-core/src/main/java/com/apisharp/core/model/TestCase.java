package com.apisharp.core.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TestCase(
        @NotBlank String projectKey,
        @NotBlank String name,
        String description,
        @Valid List<TestStep> steps
) {
    public TestCase {
        steps = steps == null ? List.of() : List.copyOf(steps);
    }
}
