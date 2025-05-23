package com.xeppelin.userservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xeppelin.userservice.domain.exception.UserDomainException;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private UUID id;

    private String name;

    private String email;

    private UserRole role;

    private UserStatus status;

    private Address address;

    public void initializeUser() {
        id = UUID.randomUUID();
        status = UserStatus.ACTIVE;
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }

    public void suspend() {
        this.status = UserStatus.SUSPENDED;
    }

    public void updateName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new UserDomainException("User name cannot be null or empty");
        }
        this.name = newName;
    }

    public void updateEmail(String newEmail) {
        if (newEmail == null || newEmail.trim().isEmpty()) {
            throw new UserDomainException("Email cannot be null or empty");
        }
        if (!isValidEmail(newEmail)) {
            throw new UserDomainException("Invalid email format");
        }
        this.email = newEmail;
    }

    public void updateRole(UserRole newRole) {
        if (newRole == null) {
            throw new UserDomainException("User role cannot be null");
        }
        this.role = newRole;
    }

    public void updateAddress(Address newAddress) {
        if (newAddress == null) {
            throw new UserDomainException("Address cannot be null");
        }
        this.address = newAddress;
    }

    @JsonIgnore
    public boolean isActive() {
        return UserStatus.ACTIVE.equals(this.status);
    }

    @JsonIgnore
    public boolean isSuspended() {
        return UserStatus.SUSPENDED.equals(this.status);
    }

    public static User createUser(String name, String email, UserRole role) {
        if (name == null || name.trim().isEmpty()) {
            throw new UserDomainException("User name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            throw new UserDomainException("Invalid email format");
        }
        if (role == null) {
            throw new UserDomainException("User role cannot be null");
        }

        return User.builder()
            .id(UUID.randomUUID())
            .name(name)
            .email(email)
            .role(role)
            .status(UserStatus.ACTIVE)
            .build();
    }

    private static boolean isValidEmail(String email) {
        // Simple email validation
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
} 