package com.xeppelin.userservice.infrastructure.adapter.input.rest.request;

import com.xeppelin.userservice.domain.model.UserRole;
import com.xeppelin.userservice.domain.model.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request payload for creating or updating a user")
public record UserRequest(
    
    @Schema(
        description = "User's full name",
        example = "John Doe",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name is required")
    String name,
    
    @Schema(
        description = "User's email address - must be unique across the system",
        example = "john.doe@example.com",
        format = "email",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,
    
    @Schema(
        description = "User's role in the system",
        example = "ATTENDEE",
        requiredMode = Schema.RequiredMode.REQUIRED,
        allowableValues = {"ADMIN", "ORGANIZER", "STAFF", "ATTENDEE"}
    )
    @NotNull(message = "Role is required")
    UserRole role,
    
    @Schema(
        description = "Current status of the user account",
        example = "ACTIVE",
        requiredMode = Schema.RequiredMode.REQUIRED,
        allowableValues = {"ACTIVE", "INACTIVE", "SUSPENDED"}
    )
    @NotNull(message = "Status is required")
    UserStatus status,
    
    @Schema(
        description = "User's address information",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Valid
    AddressRequest address
) {
}
