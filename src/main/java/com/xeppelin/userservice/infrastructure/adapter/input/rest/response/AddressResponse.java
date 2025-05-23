package com.xeppelin.userservice.infrastructure.adapter.input.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response payload containing address information")
public class AddressResponse {

    @Schema(
        description = "Unique identifier for the address",
        example = "550e8400-e29b-41d4-a716-446655440001",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String id;

    @Schema(
        description = "Primary address line (street address)",
        example = "123 Main Street"
    )
    private String line1;

    @Schema(
        description = "Secondary address line (apartment, suite, unit, etc.)",
        example = "Apt 4B"
    )
    private String line2;

    @Schema(
        description = "City name",
        example = "New York"
    )
    private String city;

    @Schema(
        description = "State or province",
        example = "NY"
    )
    private String state;

    @Schema(
        description = "Postal code or ZIP code",
        example = "10001"
    )
    private String postalCode;

    @Schema(
        description = "Country name",
        example = "United States"
    )
    private String country;

    @Schema(
        description = "Phone number with country code",
        example = "+1-555-123-4567"
    )
    private String phoneNumber;

    @Schema(
        description = "Formatted address string for display purposes",
        example = "123 Main Street, Apt 4B, New York, NY 10001, United States",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String formattedAddress;
} 