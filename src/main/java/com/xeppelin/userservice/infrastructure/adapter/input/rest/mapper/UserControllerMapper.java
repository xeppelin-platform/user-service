package com.xeppelin.userservice.infrastructure.adapter.input.rest.mapper;

import com.xeppelin.userservice.domain.model.Address;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.AddressRequest;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.request.UserRequest;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.AddressResponse;
import com.xeppelin.userservice.infrastructure.adapter.input.rest.response.UserResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true),
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserControllerMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    @Mapping(target = "address", source = "address", qualifiedByName = "fromAddressToAddressResponse")
    UserResponse fromUserToUserResponse(User user);

    @Named("uuidToString")
    default String uuidToString(java.util.UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("fromUserRequestToUser")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "address", source = "address", qualifiedByName = "fromAddressRequestToAddress")
    default User fromUserRequestToUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }

        return User.builder()
            .name(userRequest.name())
            .email(userRequest.email())
            .role(userRequest.role())
            .status(userRequest.status())
            .address(fromAddressRequestToAddress(userRequest.address()))
            .build();
    }

    @Named("fromAddressToAddressResponse")
    default AddressResponse fromAddressToAddressResponse(Address address) {
        if (address == null) {
            return null;
        }

        return AddressResponse.builder()
            .id(address.getId() != null ? address.getId().toString() : null)
            .line1(address.getLine1())
            .line2(address.getLine2())
            .city(address.getCity())
            .state(address.getState())
            .postalCode(address.getPostalCode())
            .country(address.getCountry())
            .phoneNumber(address.getPhoneNumber())
            .formattedAddress(address.getFormattedAddress())
            .build();
    }

    @Named("fromAddressRequestToAddress")
    default Address fromAddressRequestToAddress(AddressRequest addressRequest) {
        if (addressRequest == null) {
            return null;
        }

        return Address.builder()
            .line1(addressRequest.line1())
            .line2(addressRequest.line2())
            .city(addressRequest.city())
            .state(addressRequest.state())
            .postalCode(addressRequest.postalCode())
            .country(addressRequest.country())
            .phoneNumber(addressRequest.phoneNumber())
            .build();
    }
}
