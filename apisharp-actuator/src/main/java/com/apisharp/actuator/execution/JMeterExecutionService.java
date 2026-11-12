package com.apisharp.actuator.execution;

import com.apisharp.actuator.jmeter.JMeterPlanBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JMeterExecutionService {
    private final JMeterPlanBuilder planBuilder;

    public JMeterExecutionService(JMeterPlanBuilder planBuilder) {
        this.planBuilder = planBuilder;
    }

    public ExecutionReport execute(ExecutionRequest request) {
        Instant startedAt = Instant.now();
        List<String> plans = request.steps().stream()
                .map(step -> planBuilder.buildPlan(step, request.virtualUsers(), request.durationSeconds()))
                .toList();
        return new ExecutionReport(request.taskId(), "PLANNED", startedAt, Instant.now(), plans);
    }
}
