package com.apisharp.core.model;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TestTask(
        @NotBlank String projectKey,
        @NotBlank String name,
        List<String> testCaseIds,
        int virtualUsers,
        int durationSeconds
) {
    public TestTask {
        testCaseIds = testCaseIds == null ? List.of() : List.copyOf(testCaseIds);
    }
}
