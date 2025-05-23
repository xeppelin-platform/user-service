package com.xeppelin.userservice.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing the different roles a user can have in the Xeppelin platform.
 * Each role defines specific permissions and access levels within the system.
 */
@Schema(description = "User role in the Xeppelin platform", enumAsRef = true)
public enum UserRole {
    
    @Schema(description = "Administrator with full system access and management capabilities")
    ADMIN,
    
    @Schema(description = "Event organizer who can create and manage events")
    ORGANIZER,
    
    @Schema(description = "Staff member with limited administrative privileges")
    STAFF,
    
    @Schema(description = "Regular user who can attend events and purchase tickets")
    ATTENDEE
}
