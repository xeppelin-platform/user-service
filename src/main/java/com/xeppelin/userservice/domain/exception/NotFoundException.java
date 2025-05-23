package com.xeppelin.userservice.domain.exception;

public class NotFoundException extends UserDomainException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
} 