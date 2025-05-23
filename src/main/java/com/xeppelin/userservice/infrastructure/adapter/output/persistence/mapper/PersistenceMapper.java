package com.xeppelin.userservice.infrastructure.adapter.output.persistence.mapper;

import com.xeppelin.userservice.domain.model.Address;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.infrastructure.adapter.output.persistence.entity.AddressEntity;
import com.xeppelin.userservice.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PersistenceMapper {

    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserEntity fromUserToUserEntity(User user);

    @AfterMapping
    default void mapAddress(@MappingTarget UserEntity userEntity, User user) {
        if (user.getAddress() != null) {
            var address = AddressEntity.builder()
                .id(user.getAddress().getId())
                .user(userEntity)
                .line1(user.getAddress().getLine1())
                .line2(user.getAddress().getLine2())
                .city(user.getAddress().getCity())
                .state(user.getAddress().getState())
                .postalCode(user.getAddress().getPostalCode())
                .country(user.getAddress().getCountry())
                .phoneNumber(user.getAddress().getPhoneNumber())
                .build();
            userEntity.setAddress(address);
        }
    }

    @Named("fromAddressEntityToAddress")
    default Address fromAddressEntityToAddress(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        return Address.builder()
            .id(addressEntity.getId())
            .line1(addressEntity.getLine1())
            .line2(addressEntity.getLine2())
            .city(addressEntity.getCity())
            .state(addressEntity.getState())
            .postalCode(addressEntity.getPostalCode())
            .country(addressEntity.getCountry())
            .phoneNumber(addressEntity.getPhoneNumber())
            .build();
    }

    @Named("fromUserEntityToUser")
    default User fromUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return User.builder()
            .id(userEntity.getId())
            .name(userEntity.getName())
            .email(userEntity.getEmail())
            .role(userEntity.getRole())
            .status(userEntity.getStatus())
            .address(fromAddressEntityToAddress(userEntity.getAddress()))
            .build();
    }
}
