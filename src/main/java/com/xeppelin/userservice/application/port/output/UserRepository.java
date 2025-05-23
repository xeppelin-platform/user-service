package com.xeppelin.userservice.application.port.output;

import com.xeppelin.userservice.domain.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Repository interface for managing User entities.
 * This port represents the output boundary for user persistence operations.
 */
public interface UserRepository {

    /**
     * Saves a user entity to the repository.
     *
     * @param user the user to save
     * @return the saved user with any modifications made during persistence
     */
    User save(User user);

    /**
     * Finds a user by their unique identifier.
     *
     * @param id the UUID of the user to find
     * @return an Optional containing the found user or empty if not found
     */
    Optional<User> findById(UUID id);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the found user or empty if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return an Optional containing the found user or empty if not found
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

    /**
     * Retrieves all users with pagination support.
     *
     * @param pageable pagination information
     * @return a page of users
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the UUID of the user to delete
     */
    void deleteById(UUID id);
}
