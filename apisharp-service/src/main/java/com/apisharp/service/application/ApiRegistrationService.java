package com.apisharp.service.application;

import com.apisharp.core.compliance.ComplianceEngine;
import com.apisharp.core.compliance.ComplianceFinding;
import com.apisharp.core.documentation.MarkdownDocumentGenerator;
import com.apisharp.core.model.ApiDefinition;
import com.apisharp.service.store.InMemoryRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiRegistrationService {
    private final InMemoryRegistry registry;
    private final ComplianceEngine complianceEngine = ComplianceEngine.defaultEngine();
    private final MarkdownDocumentGenerator documentGenerator = new MarkdownDocumentGenerator();

    public ApiRegistrationService(InMemoryRegistry registry) {
        this.registry = registry;
    }

    public RegisteredApi register(ApiDefinition apiDefinition) {
        List<ComplianceFinding> findings = complianceEngine.review(apiDefinition);
        String id = registry.saveApi(apiDefinition);
        String document = documentGenerator.generate(apiDefinition);
        return new RegisteredApi(id, apiDefinition, findings, document);
    }

    public record RegisteredApi(String id, ApiDefinition apiDefinition, List<ComplianceFinding> findings, String markdownDocument) {
    }
}
