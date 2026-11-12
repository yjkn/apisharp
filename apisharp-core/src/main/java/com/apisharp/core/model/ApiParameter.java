package com.apisharp.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApiParameter(
        @NotBlank String name,
        @NotNull ParameterLocation location,
        @NotBlank String type,
        boolean required,
        String description
) {
}
