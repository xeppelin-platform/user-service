package com.xeppelin.userservice.application.service;

import com.xeppelin.userservice.application.port.input.UserManagementUseCase;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.domain.service.UserDomainService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserManagementUseCase {

    private final UserDomainService userDomainService;

    @Override
    @Transactional
    @CachePut(value = "User", key = "#result.id")
    public User createUser(User user) {
        log.info("Creating new user with email: {}", user.getEmail());
        User newUser = userDomainService.createUser(user);
        log.info("User created with ID: {}", newUser.getId());
        return newUser;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "UserByEmail", key = "#email", unless = "#result == null")
    public User getUserByEmail(String email) {
        log.debug("Getting user by email: {}", email);
        return userDomainService.getUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "User", key = "#userId", unless = "#result == null")
    public User getUserById(UUID userId) {
        log.debug("Getting user by ID: {}", userId);
        return userDomainService.getUserById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "UserByPhone", key = "#phoneNumber", unless = "#result == null")
    public User getUserByPhoneNumber(String phoneNumber) {
        log.debug("Getting user by phone number: {}", phoneNumber);
        return userDomainService.getUserByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable) {
        log.debug("Getting all users with pagination: {}", pageable);
        return userDomainService.getAllUsers(pageable);
    }

    @Override
    @Transactional
    @CachePut(value = "User", key = "#userId")
    public User updateUser(UUID userId, User user) {
        log.info("Updating user with ID: {}", userId);
        User updatedUser = userDomainService.updateUser(userId, user);
        log.info("User updated with email: {}", updatedUser.getEmail());
        return updatedUser;
    }

    @Override
    @Transactional
    @CacheEvict(value = "User", key = "#userId")
    public void deleteUser(UUID userId) {
        log.info("Deleting user with ID: {}", userId);
        userDomainService.deleteUser(userId);
        log.info("User deleted with ID: {}", userId);
    }
}
