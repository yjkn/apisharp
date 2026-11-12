package com.apisharp.service.api;

import com.apisharp.core.model.TestCase;
import com.apisharp.core.model.TestTask;
import com.apisharp.service.store.InMemoryRegistry;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/testing")
public class TestManagementController {
    private final InMemoryRegistry registry;

    public TestManagementController(InMemoryRegistry registry) {
        this.registry = registry;
    }

    @PostMapping("/cases")
    public Map<String, String> createCase(@Valid @RequestBody TestCase testCase) {
        return Map.of("id", registry.saveTestCase(testCase));
    }

    @GetMapping("/cases")
    public Collection<TestCase> cases() {
        return registry.testCases();
    }

    @PostMapping("/tasks")
    public Map<String, String> createTask(@Valid @RequestBody TestTask testTask) {
        return Map.of("id", registry.saveTestTask(testTask));
    }

    @GetMapping("/tasks")
    public Collection<TestTask> tasks() {
        return registry.testTasks();
    }
}
