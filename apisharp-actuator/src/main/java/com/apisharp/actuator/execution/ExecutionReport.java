package com.apisharp.actuator.execution;

import java.time.Instant;
import java.util.List;

public record ExecutionReport(
        String taskId,
        String status,
        Instant startedAt,
        Instant finishedAt,
        List<String> generatedPlans
) {
}
