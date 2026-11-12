package com.apisharp.core.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ApiResponse(
        @Min(100) @Max(599) int status,
        String mediaType,
        String schema,
        String description
) {
}
