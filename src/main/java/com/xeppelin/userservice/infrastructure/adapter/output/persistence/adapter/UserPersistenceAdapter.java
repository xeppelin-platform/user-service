package com.xeppelin.userservice.infrastructure.adapter.output.persistence.adapter;

import com.xeppelin.userservice.application.port.output.UserRepository;
import com.xeppelin.userservice.domain.model.User;
import com.xeppelin.userservice.infrastructure.adapter.output.persistence.mapper.PersistenceMapper;
import com.xeppelin.userservice.infrastructure.adapter.output.persistence.repository.UserJpaRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final PersistenceMapper persistenceMapper;

    @Override
    public User save(User user) {
        var userEntity = persistenceMapper.fromUserToUserEntity(user);
        var savedUserEntity = userJpaRepository.save(userEntity);
        return persistenceMapper.fromUserEntityToUser(savedUserEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id)
            .map(persistenceMapper::fromUserEntityToUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
            .map(persistenceMapper::fromUserEntityToUser);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userJpaRepository.findByPhoneNumber(phoneNumber)
            .map(persistenceMapper::fromUserEntityToUser);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userJpaRepository.findAll(pageable)
            .map(persistenceMapper::fromUserEntityToUser);
    }

    @Override
    public void deleteById(UUID id) {
        userJpaRepository.deleteById(id);
    }
}
