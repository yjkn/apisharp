package com.apisharp.core.compliance;

import com.apisharp.core.model.ApiDefinition;

import java.util.List;

public final class ComplianceEngine {
    private final List<ComplianceRule> rules;

    public ComplianceEngine(List<ComplianceRule> rules) {
        this.rules = List.copyOf(rules);
    }

    public static ComplianceEngine defaultEngine() {
        return new ComplianceEngine(List.of(new PathNamingRule(), new ResponseDefinitionRule()));
    }

    public List<ComplianceFinding> review(ApiDefinition apiDefinition) {
        return rules.stream()
                .flatMap(rule -> rule.validate(apiDefinition).stream())
                .toList();
    }
}
