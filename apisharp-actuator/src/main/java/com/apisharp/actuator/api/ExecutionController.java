package com.apisharp.actuator.api;

import com.apisharp.actuator.execution.ExecutionReport;
import com.apisharp.actuator.execution.ExecutionRequest;
import com.apisharp.actuator.execution.JMeterExecutionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/executions")
public class ExecutionController {
    private final JMeterExecutionService executionService;

    public ExecutionController(JMeterExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping
    public ExecutionReport execute(@Valid @RequestBody ExecutionRequest request) {
        return executionService.execute(request);
    }
}
