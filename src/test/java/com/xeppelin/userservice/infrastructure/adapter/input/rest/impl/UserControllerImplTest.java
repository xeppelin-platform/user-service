package com.xeppelin.userservice.infrastructure.adapter.input.rest.impl;

import com.xeppelin.userservice.application.port.input.UserManagementUseCase;
import com.xeppelin.userservice.domain.model.Address;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.domain.model.UserRole;
import com.xeppelin.userservice.domain.model.UserStatus;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.mapper.UserControllerMapper;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.AddressRequest;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.UserRequest;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.AddressResponse;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.PagedResponse;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.UserResponse;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @Mock
    private UserManagementUseCase userManagementUseCase;

    @Mock
    private UserControllerMapper userControllerMapper;

    @InjectMocks
    private UserControllerImpl controller;

    private UUID userId;
    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private AddressRequest addressRequest;
    private Address address;
    private AddressResponse addressResponse;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        addressRequest = new AddressRequest(
            "123 Main St",
            "Apt 4B",
            "New York",
            "NY",
            "10001",
            "USA",
            "+1-555-123-4567"
        );

        address = Address.builder()
            .id(UUID.randomUUID())
            .line1("123 Main St")
            .line2("Apt 4B")
            .city("New York")
            .state("NY")
            .postalCode("10001")
            .country("USA")
            .phoneNumber("+1-555-123-4567")
            .build();

        addressResponse = AddressResponse.builder()
            .id(address.getId().toString())
            .line1("123 Main St")
            .line2("Apt 4B")
            .city("New York")
            .state("NY")
            .postalCode("10001")
            .country("USA")
            .phoneNumber("+1-555-123-4567")
            .build();

        userRequest = new UserRequest(
            "John Doe",
            "john.doe@example.com",
            UserRole.ATTENDEE,
            UserStatus.ACTIVE,
            addressRequest
        );

        user = User.builder()
            .id(userId)
            .name("John Doe")
            .email("john.doe@example.com")
            .role(UserRole.ATTENDEE)
            .status(UserStatus.ACTIVE)
            .address(address)
            .build();

        userResponse = UserResponse.builder()
            .id(userId.toString())
            .name("John Doe")
            .email("john.doe@example.com")
            .role(UserRole.ATTENDEE)
            .status(UserStatus.ACTIVE)
            .address(addressResponse)
            .build();
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        // Arrange
        when(userControllerMapper.fromUserRequestToUser(userRequest)).thenReturn(user);
        when(userManagementUseCase.createUser(user)).thenReturn(user);
        when(userControllerMapper.fromUserToUserResponse(user)).thenReturn(userResponse);

        // Act
        UserResponse result = controller.createUser(userRequest);

        // Assert
        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getName(), result.getName());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getRole(), result.getRole());
        assertEquals(userResponse.getStatus(), result.getStatus());
        verify(userManagementUseCase).createUser(user);
    }

    @Test
    void getUserById_ShouldReturnUser() {
        // Arrange
        when(userManagementUseCase.getUserById(userId)).thenReturn(user);
        when(userControllerMapper.fromUserToUserResponse(user)).thenReturn(userResponse);

        // Act
        UserResponse result = controller.getUserById(userId.toString());

        // Assert
        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getName(), result.getName());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getRole(), result.getRole());
        assertEquals(userResponse.getStatus(), result.getStatus());
    }

    @Test
    void getAllUsers_ShouldReturnPagedResponse() {
        // Arrange
        org.springdoc.core.converters.models.Pageable springdocPageable =
            mock(org.springdoc.core.converters.models.Pageable.class);
        when(springdocPageable.getPage()).thenReturn(0);
        when(springdocPageable.getSize()).thenReturn(10);

        // Create the expected Spring Data PageRequest that should be passed to the use case
        PageRequest expectedPageRequest = PageRequest.of(0, 10);
        List<User> users = List.of(user);
        Page<User> userPage = new PageImpl<>(users, expectedPageRequest, 1);

        // Verify that the exact PageRequest is used
        when(userManagementUseCase.getAllUsers(expectedPageRequest)).thenReturn(userPage);
        when(userControllerMapper.fromUserToUserResponse(user)).thenReturn(userResponse);

        // Act
        PagedResponse<UserResponse> result = controller.getAllUsers(springdocPageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(userResponse, result.getContent().get(0));
        assertEquals(1, result.getMetadata().getTotalElements());
        assertEquals(1, result.getMetadata().getTotalPages());
        assertEquals(10, result.getMetadata().getSize());
        assertEquals(0, result.getMetadata().getNumber());

        // Verify the exact PageRequest was used
        verify(userManagementUseCase).getAllUsers(expectedPageRequest);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        // Arrange
        when(userControllerMapper.fromUserRequestToUser(userRequest)).thenReturn(user);
        when(userManagementUseCase.updateUser(userId, user)).thenReturn(user);
        when(userControllerMapper.fromUserToUserResponse(user)).thenReturn(userResponse);

        // Act
        UserResponse result = controller.updateUser(userId.toString(), userRequest);

        // Assert
        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getName(), result.getName());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getRole(), result.getRole());
        assertEquals(userResponse.getStatus(), result.getStatus());
        verify(userManagementUseCase).updateUser(userId, user);
    }

    @Test
    void deleteUser_ShouldCallUserManagementUseCase() {
        // Arrange
        doNothing().when(userManagementUseCase).deleteUser(userId);

        // Act
        controller.deleteUser(userId.toString());

        // Assert
        verify(userManagementUseCase).deleteUser(userId);
    }
} 