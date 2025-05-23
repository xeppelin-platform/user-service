package com.xeppelin.userservice.infrastructure.adapter.input.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Request payload for user address information")
public record AddressRequest(
    @Schema(
        description = "Primary address line (street address)",
        example = "123 Main Street",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Address line 1 is required")
    String line1,
    
    @Schema(
        description = "Secondary address line (apartment, suite, unit, etc.)",
        example = "Apt 4B",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    String line2,
    
    @Schema(
        description = "City name",
        example = "New York",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "City is required")
    String city,
    
    @Schema(
        description = "State or province",
        example = "NY",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "State is required")
    String state,
    
    @Schema(
        description = "Postal code or ZIP code",
        example = "10001",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Postal code is required")
    String postalCode,
    
    @Schema(
        description = "Country name",
        example = "United States",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Country is required")
    String country,
    
    @Schema(
        description = "Phone number with country code",
        example = "+1-555-123-4567",
        pattern = "^[\\d\\s\\-\\(\\)\\+]+$",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[\\d\\s\\-\\(\\)\\+]+$", message = "Invalid phone number format")
    String phoneNumber
) {
} 