package com.xeppelin.userservice.domain.service;

import com.xeppelin.userservice.domain.model.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing user domain operations.
 * This interface defines the core business operations related to user management
 * in the domain layer, following the hexagonal architecture pattern.
 */
public interface UserDomainService {

    /**
     * Creates a new user in the system.
     *
     * @param user The user entity to be created
     * @return The created user with assigned identifier
     */
    User createUser(User user);

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The unique identifier of the user
     * @return The user entity if found
     * @throws com.xeppelin.userservice.domain.exception.UserNotFoundException if no user exists with the given ID
     */
    User getUserById(UUID userId);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user
     * @return The user entity if found
     * @throws com.xeppelin.userservice.domain.exception.UserNotFoundException if no user exists with the given email
     */
    User getUserByEmail(String email);

    /**
     * Retrieves a user by their phone number.
     *
     * @param phoneNumber The phone number of the user
     * @return The user entity if found
     * @throws com.xeppelin.userservice.domain.exception.UserNotFoundException if no user exists with the given phone number
     */
    User getUserByPhoneNumber(String phoneNumber);

    /**
     * Retrieves all users with pagination support.
     *
     * @param pageable The pagination information
     * @return The page of users
     */
    Page<User> getAllUsers(Pageable pageable);

    /**
     * Updates an existing user's information.
     *
     * @param user The user entity with updated information
     * @return The updated user entity
     * @throws com.xeppelin.userservice.domain.exception.UserNotFoundException if the user to update doesn't exist
     */
    User updateUser(UUID userId, User user);

    /**
     * Deletes a user from the system.
     *
     * @param userId The unique identifier of the user to be deleted
     * @throws com.xeppelin.userservice.domain.exception.UserNotFoundException if no user exists with the given ID
     */
    void deleteUser(UUID userId);
}
