package com.xeppelin.userservice.domain.service.impl;

import com.xeppelin.userservice.application.port.output.UserRepository;
import com.xeppelin.userservice.domain.exception.NotFoundException;
import com.xeppelin.userservice.domain.exception.UserDomainException;
import com.xeppelin.userservice.domain.model.Address;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.domain.service.UserDomainService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        log.info("Creating user: {}", user);

        // Validate the user before saving
        validateUser(user);

        // Initialize user (set any default values or generated fields)
        user.initializeUser();

        // Initialize address if it exists
        if (user.getAddress() != null) {
            user.getAddress().initializeAddress();
            user.getAddress().updateUser(user);
        }

        // Check if user with email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("User with email {} already exists", user.getEmail());
            throw new UserDomainException(String.format("User with email %s already exists", user.getEmail()));
        }

        // Save the user
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        log.info("Getting user by ID: {}", userId);
        return userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(
                String.format("User not found with ID: %s", userId)
            ));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Getting user by email: {}", email);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException(
                String.format("User not found with email: %s", email)
            ));
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        log.info("Getting user by phone number: {}", phoneNumber);
        return userRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new NotFoundException(
                String.format("User not found with phone number: %s", phoneNumber)
            ));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        log.info("Getting all users with pageable: {}", pageable);
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(UUID userId, User user) {
        log.info("Updating user: {}", user);

        // Validate user exists
        var userToUpdate = getUserById(userId);

        var address = Address.builder()
            .id(userToUpdate.getAddress().getId())
            .user(
                User.builder()
                    .id(userToUpdate.getId())
                    .build()
            )
            .line1(user.getAddress().getLine1())
            .line2(user.getAddress().getLine2())
            .city(user.getAddress().getCity())
            .state(user.getAddress().getState())
            .postalCode(user.getAddress().getPostalCode())
            .country(user.getAddress().getCountry())
            .phoneNumber(user.getAddress().getPhoneNumber())
            .build();

        var newUser = User.builder()
            .id(userToUpdate.getId())
            .name(user.getName())
            .email(user.getEmail())
            .role(user.getRole())
            .status(user.getStatus())
            .address(address)
            .build();

        // Validate updated user data
        validateUser(newUser);

        // Save the updated user
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        log.info("Deleting user with ID: {}", userId);

        // Verify user exists before deletion
        var user = getUserById(userId);

        // Delete the user
        userRepository.deleteById(user.getId());
        log.info("User with ID: {} successfully deleted", userId);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new UserDomainException("User cannot be null");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new UserDomainException("User email cannot be empty");
        }

        // Add more validation logic as needed
    }
}
