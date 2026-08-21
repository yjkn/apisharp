package com.apisharp.core.compliance;

import com.apisharp.core.model.ApiDefinition;

import java.util.List;

public final class ResponseDefinitionRule implements ComplianceRule {
    @Override
    public String code() {
        return "API_RESPONSE_REQUIRED";
    }

    @Override
    public List<ComplianceFinding> validate(ApiDefinition apiDefinition) {
        if (!apiDefinition.responses().isEmpty()) {
            return List.of();
        }
        return List.of(new ComplianceFinding(code(), ComplianceFinding.Severity.WARNING,
                "API should define at least one response for documentation and test assertions."));
    }
}
