package com.apisharp.core.compliance;

import com.apisharp.core.model.ApiDefinition;

import java.util.List;
import java.util.regex.Pattern;

public final class PathNamingRule implements ComplianceRule {
    private static final Pattern PATH_PATTERN = Pattern.compile("^/[a-z0-9/{}/-]+$");

    @Override
    public String code() {
        return "API_PATH_NAMING";
    }

    @Override
    public List<ComplianceFinding> validate(ApiDefinition apiDefinition) {
        if (PATH_PATTERN.matcher(apiDefinition.path()).matches()) {
            return List.of();
        }
        return List.of(new ComplianceFinding(code(), ComplianceFinding.Severity.ERROR,
                "API path must start with '/' and use lowercase letters, numbers, hyphens, and path variables."));
    }
}
