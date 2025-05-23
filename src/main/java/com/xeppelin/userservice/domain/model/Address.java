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
public class Address implements Serializable {

    private UUID id;

    private User user;

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String phoneNumber;

    public void initializeAddress() {
        id = UUID.randomUUID();
    }

    public void updateUser(User user) {
        this.user = user;
    }

    public void updateLine1(String newLine1) {
        if (newLine1 == null || newLine1.trim().isEmpty()) {
            throw new UserDomainException("Address line 1 cannot be null or empty");
        }
        this.line1 = newLine1;
    }

    public void updateLine2(String newLine2) {
        this.line2 = newLine2;
    }

    public void updateCity(String newCity) {
        if (newCity == null || newCity.trim().isEmpty()) {
            throw new UserDomainException("City cannot be null or empty");
        }
        this.city = newCity;
    }

    public void updateState(String newState) {
        if (newState == null || newState.trim().isEmpty()) {
            throw new UserDomainException("State cannot be null or empty");
        }
        this.state = newState;
    }

    public void updatePostalCode(String newPostalCode) {
        if (newPostalCode == null || newPostalCode.trim().isEmpty()) {
            throw new UserDomainException("Postal code cannot be null or empty");
        }
        this.postalCode = newPostalCode;
    }

    public void updateCountry(String newCountry) {
        if (newCountry == null || newCountry.trim().isEmpty()) {
            throw new UserDomainException("Country cannot be null or empty");
        }
        this.country = newCountry;
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
            throw new UserDomainException("Phone number cannot be null or empty");
        }
        if (!isValidPhoneNumber(newPhoneNumber)) {
            throw new UserDomainException("Invalid phone number format");
        }
        this.phoneNumber = newPhoneNumber;
    }

    public static Address createAddress(User user, String line1, String line2,
                                        String city, String state, String postalCode, String country, String phoneNumber) {
        return Address.builder()
            .id(UUID.randomUUID())
            .user(user)
            .line1(line1)
            .line2(line2)
            .city(city)
            .state(state)
            .postalCode(postalCode)
            .country(country)
            .phoneNumber(phoneNumber)
            .build();
    }

    @JsonIgnore
    public boolean isValid() {
        return line1 != null && !line1.isBlank() &&
            city != null && !city.isBlank() &&
            state != null && !state.isBlank() &&
            postalCode != null && !postalCode.isBlank() &&
            country != null && !country.isBlank() &&
            phoneNumber != null && !phoneNumber.isBlank();
    }

    @JsonIgnore
    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(line1);

        if (line2 != null && !line2.isBlank()) {
            sb.append(", ").append(line2);
        }

        sb.append(", ")
            .append(city)
            .append(", ")
            .append(state)
            .append(" ")
            .append(postalCode)
            .append(", ")
            .append(country);

        return sb.toString();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Simple validation: Allow digits, spaces, dashes, and parentheses, min 7 digits
        return phoneNumber != null &&
            phoneNumber.matches("^[\\d\\s\\-\\(\\)\\+]+$") &&
            phoneNumber.replaceAll("[^\\d]", "").length() >= 7;
    }
} 