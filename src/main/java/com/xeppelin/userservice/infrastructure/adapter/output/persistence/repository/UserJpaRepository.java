package com.xeppelin.userservice.infrastructure.adapter.output.persistence.repository;

import com.xeppelin.userservice.infrastructure.adapter.output.persistence.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u JOIN u.address a WHERE a.phoneNumber = :phoneNumber")
    Optional<UserEntity> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
