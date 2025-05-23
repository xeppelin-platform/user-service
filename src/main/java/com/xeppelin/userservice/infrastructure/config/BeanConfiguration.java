package com.xeppelin.userservice.infrastructure.config;

import com.xeppelin.userservice.application.port.output.UserRepository;
import com.xeppelin.userservice.domain.service.UserDomainService;
import com.xeppelin.userservice.domain.service.impl.UserDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserDomainService userDomainService(UserRepository userRepository) {
        return new UserDomainServiceImpl(userRepository);
    }
}
