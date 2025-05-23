package com.xeppelin.userservice.application.port.input;

import com.xeppelin.userservice.domain.model.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for user management operations following the ports and adapters pattern.
 * Defines the primary operations that can be performed on users within the system.
 */
public interface UserManagementUseCase {

    /**
     * Creates a new user in the system.
     *
     * @param user the user details to be created
     * @return the created user with generated ID and any other system-assigned attributes
     */
    User createUser(User user);

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return the user with the specified email address
     */
    User getUserByEmail(String email);

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId the unique identifier of the user
     * @return the user with the specified ID
     */
    User getUserById(UUID userId);

    /**
     * Retrieves a user by their phone number.
     *
     * @param phoneNumber the phone number of the user to retrieve
     * @return the user with the specified phone number
     */
    User getUserByPhoneNumber(String phoneNumber);

    /**
     * Retrieves all users with pagination support.
     *
     * @param pageable pagination information including page number, size, and sorting
     * @return a page of users according to the pagination parameters
     */
    Page<User> getAllUsers(Pageable pageable);

    /**
     * Updates an existing user's information.
     *
     * @param userId
     * @param user   the user with updated information
     * @return the updated user entity
     */
    User updateUser(UUID userId, User user);

    /**
     * Deletes a user from the system.
     *
     * @param userId the unique identifier of the user to delete
     */
    void deleteUser(UUID userId);
}
