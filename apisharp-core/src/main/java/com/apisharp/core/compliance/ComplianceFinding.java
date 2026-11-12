package com.apisharp.core.compliance;

public record ComplianceFinding(String ruleCode, Severity severity, String message) {
    public enum Severity {
        INFO, WARNING, ERROR
    }
}
