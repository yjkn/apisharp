package com.apisharp.actuator.execution;

import com.apisharp.core.model.TestStep;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ExecutionRequest(
        @NotBlank String taskId,
        @Valid List<TestStep> steps,
        int virtualUsers,
        int durationSeconds
) {
    public ExecutionRequest {
        steps = steps == null ? List.of() : List.copyOf(steps);
    }
}
