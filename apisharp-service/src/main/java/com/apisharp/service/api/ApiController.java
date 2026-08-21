package com.apisharp.service.api;

import com.apisharp.core.model.ApiDefinition;
import com.apisharp.service.application.ApiRegistrationService;
import com.apisharp.service.store.InMemoryRegistry;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api-definitions")
public class ApiController {
    private final ApiRegistrationService registrationService;
    private final InMemoryRegistry registry;

    public ApiController(ApiRegistrationService registrationService, InMemoryRegistry registry) {
        this.registrationService = registrationService;
        this.registry = registry;
    }

    @PostMapping
    public ApiRegistrationService.RegisteredApi register(@Valid @RequestBody ApiDefinition apiDefinition) {
        return registrationService.register(apiDefinition);
    }

    @GetMapping
    public Collection<ApiDefinition> list() {
        return registry.apis();
    }
}
