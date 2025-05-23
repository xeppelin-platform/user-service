package com.xeppelin.userservice.infrastructure.adapter.input.rest.response;

import com.xeppelin.userservice.domain.model.UserRole;
import com.xeppelin.userservice.domain.model.UserStatus;
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
@Schema(description = "Response payload containing user information")
public class UserResponse {

    @Schema(
        description = "Unique identifier for the user",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String id;

    @Schema(
        description = "User's full name",
        example = "John Doe"
    )
    private String name;

    @Schema(
        description = "User's email address",
        example = "john.doe@example.com",
        format = "email"
    )
    private String email;

    @Schema(
        description = "User's role in the system",
        example = "ATTENDEE",
        allowableValues = {"ADMIN", "ORGANIZER", "STAFF", "ATTENDEE"}
    )
    private UserRole role;

    @Schema(
        description = "Current status of the user account",
        example = "ACTIVE",
        allowableValues = {"ACTIVE", "INACTIVE", "SUSPENDED"}
    )
    private UserStatus status;

    @Schema(
        description = "User's address information"
    )
    private AddressResponse address;
}
