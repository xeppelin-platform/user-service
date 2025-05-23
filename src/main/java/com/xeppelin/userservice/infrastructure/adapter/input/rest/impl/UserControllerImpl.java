package com.xeppelin.userservice.infrastructure.adapter.input.rest.impl;

import com.xeppelin.userservice.application.port.input.UserManagementUseCase;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.IUserController;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.mapper.UserControllerMapper;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.UserRequest;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.PagedResponse;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.UserResponse;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {

    private final UserManagementUseCase userManagementUseCase;

    private final UserControllerMapper userControllerMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating user with request: {}", userRequest);
        var user = userControllerMapper.fromUserRequestToUser(userRequest);
        var newUser = userManagementUseCase.createUser(user);
        return userControllerMapper.fromUserToUserResponse(newUser);
    }

    @Override
    public UserResponse getUserById(String userId) {
        log.info("Fetching user with ID: {}", userId);
        var user = userManagementUseCase.getUserById(UUID.fromString(userId));
        return userControllerMapper.fromUserToUserResponse(user);
    }

    @Override
    public PagedResponse<UserResponse> getAllUsers(Pageable pageable) {
        log.info("Fetching all users with pagination: {}", pageable);
        // Convert springdoc Pageable to Spring Data Pageable
        var springDataPageable = PageRequest.of(pageable.getPage(), pageable.getSize());
        var usersPage = userManagementUseCase.getAllUsers(springDataPageable);

        // Transform User objects to UserResponse objects
        var userResponses = usersPage.getContent()
            .stream()
            .map(userControllerMapper::fromUserToUserResponse)
            .collect(Collectors.toList());

        // Create PagedUserResponse with content and metadata
        var metadata = new PagedResponse.PageMetadata(
            usersPage.getSize(),
            usersPage.getNumber(),
            usersPage.getTotalElements(),
            usersPage.getTotalPages()
        );

        return PagedResponse.<UserResponse>builder()
            .content(userResponses)
            .metadata(metadata)
            .build();
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest userRequest) {
        log.info("Updating user with ID: {} and request: {}", userId, userRequest);
        var userToUpdate = userControllerMapper.fromUserRequestToUser(userRequest);
        var updatedUser = userManagementUseCase.updateUser(UUID.fromString(userId), userToUpdate);
        return userControllerMapper.fromUserToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(String userId) {
        log.info("Deleting user with ID: {}", userId);
        userManagementUseCase.deleteUser(UUID.fromString(userId));
    }
}
