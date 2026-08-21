package com.apisharp.core.compliance;

import com.apisharp.core.model.ApiDefinition;

import java.util.List;

public interface ComplianceRule {
    String code();

    List<ComplianceFinding> validate(ApiDefinition apiDefinition);
}
