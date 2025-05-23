package com.xeppelin.userservice.infrastructure.adapter.input.rest.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard error response format")
public class ErrorResponse {

    @Schema(
        description = "Timestamp when the error occurred",
        example = "2024-01-15T10:30:00Z"
    )
    private Instant timestamp;

    @Schema(
        description = "HTTP status code",
        example = "400"
    )
    private int status;

    @Schema(
        description = "Error type or category",
        example = "VALIDATION_ERROR"
    )
    private String error;

    @Schema(
        description = "Brief description of the error",
        example = "Invalid input data"
    )
    private String message;

    @Schema(
        description = "Detailed error description",
        example = "The provided email address is not in a valid format"
    )
    private String details;

    @Schema(
        description = "API path where the error occurred",
        example = "/api/user-service/users"
    )
    private String path;

    @Schema(
        description = "List of validation errors (if applicable)"
    )
    private List<ValidationError> validationErrors;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Validation error details")
    public static class ValidationError {

        @Schema(
            description = "Field name that failed validation",
            example = "email"
        )
        private String field;

        @Schema(
            description = "Rejected value",
            example = "invalid-email"
        )
        private Object rejectedValue;

        @Schema(
            description = "Validation error message",
            example = "Invalid email format"
        )
        private String message;
    }
} 