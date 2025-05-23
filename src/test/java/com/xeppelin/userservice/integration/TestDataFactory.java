package com.xeppelin.userservice.integration;

import com.xeppelin.userservice.domain.model.Address;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.domain.model.UserRole;
import com.xeppelin.userservice.domain.model.UserStatus;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.AddressRequest;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.UserRequest;

import java.util.UUID;

/**
 * Factory class for creating test data objects used in integration tests.
 * Provides pre-configured test objects for User, Address, and their Request DTOs.
 */
public class TestDataFactory {

    public static UserRequest createValidUserRequest() {
        return new UserRequest(
            "John Doe",
            "john.doe@example.com",
            UserRole.ATTENDEE,
            UserStatus.ACTIVE,
            createValidAddressRequest()
        );
    }

    public static UserRequest createValidUserRequestWithoutAddress() {
        return new UserRequest(
            "Jane Smith",
            "jane.smith@example.com",
            UserRole.STAFF,
            UserStatus.ACTIVE,
            null
        );
    }

    public static UserRequest createAdminUserRequest() {
        return new UserRequest(
            "Admin User",
            "admin@xeppelin.com",
            UserRole.ADMIN,
            UserStatus.ACTIVE,
            createValidAddressRequest()
        );
    }

    public static UserRequest createOrganizerUserRequest() {
        return new UserRequest(
            "Event Organizer",
            "organizer@xeppelin.com",
            UserRole.ORGANIZER,
            UserStatus.ACTIVE,
            createValidAddressRequest()
        );
    }

    public static UserRequest createInactiveUserRequest() {
        return new UserRequest(
            "Inactive User",
            "inactive@example.com",
            UserRole.ATTENDEE,
            UserStatus.INACTIVE,
            createValidAddressRequest()
        );
    }

    public static UserRequest createUserRequestWithInvalidEmail() {
        return new UserRequest(
            "Test User",
            "invalid-email",
            UserRole.ATTENDEE,
            UserStatus.ACTIVE,
            createValidAddressRequest()
        );
    }

    public static UserRequest createUserRequestWithBlankName() {
        return new UserRequest(
            "",
            "test@example.com",
            UserRole.ATTENDEE,
            UserStatus.ACTIVE,
            createValidAddressRequest()
        );
    }

    public static UserRequest createUpdateUserRequest() {
        return new UserRequest(
            "Updated Name",
            "updated@example.com",
            UserRole.ORGANIZER,
            UserStatus.ACTIVE,
            createUpdatedAddressRequest()
        );
    }

    public static AddressRequest createValidAddressRequest() {
        return new AddressRequest(
            "123 Main Street",
            "Apt 4B",
            "New York",
            "NY",
            "10001",
            "United States",
            "+1-555-123-4567"
        );
    }

    public static AddressRequest createUpdatedAddressRequest() {
        return new AddressRequest(
            "456 Updated Avenue",
            "Suite 200",
            "Los Angeles",
            "CA",
            "90210",
            "United States",
            "+1-555-987-6543"
        );
    }

    public static AddressRequest createAddressRequestWithInvalidPhone() {
        return new AddressRequest(
            "123 Main Street",
            "Apt 4B",
            "New York",
            "NY",
            "10001",
            "United States",
            "invalid-phone"
        );
    }

    public static AddressRequest createAddressRequestWithBlankCity() {
        return new AddressRequest(
            "123 Main Street",
            "Apt 4B",
            "",
            "NY",
            "10001",
            "United States",
            "+1-555-123-4567"
        );
    }

    public static User createValidUser() {
        return User.builder()
            .id(UUID.randomUUID())
            .name("Test User")
            .email("test@example.com")
            .role(UserRole.ATTENDEE)
            .status(UserStatus.ACTIVE)
            .address(createValidAddress())
            .build();
    }

    public static Address createValidAddress() {
        return Address.builder()
            .id(UUID.randomUUID())
            .line1("123 Test Street")
            .line2("Apt 1A")
            .city("Test City")
            .state("Test State")
            .postalCode("12345")
            .country("Test Country")
            .phoneNumber("+1-555-123-4567")
            .build();
    }

    public static String generateUniqueEmail() {
        return "test-" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public static String generateInvalidUuid() {
        return "invalid-uuid-format";
    }

    public static String generateValidUuid() {
        return UUID.randomUUID().toString();
    }
} 