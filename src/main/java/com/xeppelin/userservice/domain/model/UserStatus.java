package com.xeppelin.userservice.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing the different statuses a user account can have.
 * These statuses control user access and account state within the system.
 */
@Schema(description = "User account status", enumAsRef = true)
public enum UserStatus {
    
    @Schema(description = "Active user account with full access to the platform")
    ACTIVE,
    
    @Schema(description = "Inactive user account - user cannot access the platform")
    INACTIVE,
    
    @Schema(description = "Suspended user account - temporarily restricted access due to policy violations")
    SUSPENDED
}
