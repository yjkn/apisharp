package com.apisharp.service.store;

import com.apisharp.core.model.ApiDefinition;
import com.apisharp.core.model.TestCase;
import com.apisharp.core.model.TestTask;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRegistry {
    private final Map<String, ApiDefinition> apis = new ConcurrentHashMap<>();
    private final Map<String, TestCase> testCases = new ConcurrentHashMap<>();
    private final Map<String, TestTask> testTasks = new ConcurrentHashMap<>();

    public String saveApi(ApiDefinition apiDefinition) {
        String id = apiDefinition.projectKey() + ":" + apiDefinition.method() + ":" + apiDefinition.path();
        apis.put(id, apiDefinition);
        return id;
    }

    public Collection<ApiDefinition> apis() {
        return apis.values();
    }

    public String saveTestCase(TestCase testCase) {
        String id = UUID.randomUUID().toString();
        testCases.put(id, testCase);
        return id;
    }

    public Collection<TestCase> testCases() {
        return testCases.values();
    }

    public String saveTestTask(TestTask testTask) {
        String id = UUID.randomUUID().toString();
        testTasks.put(id, testTask);
        return id;
    }

    public Collection<TestTask> testTasks() {
        return testTasks.values();
    }
}
