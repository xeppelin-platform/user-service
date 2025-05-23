package com.xeppelin.userservice.infrastructure.adapter.input.rest.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.xeppelin.userservice.domain.exception.NotFoundException;
import com.xeppelin.userservice.domain.exception.UserDomainException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the User Service API.
 * Provides standardized error responses for all exceptions.
 */
@Slf4j
@RestControllerAdvice
@Hidden // Hide from OpenAPI documentation
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(
        responseCode = "422",
        description = "Validation error",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<ErrorResponse> handleValidationException(
        MethodArgumentNotValidException ex,
        HttpServletRequest request) {

        log.warn("Validation error: {}", ex.getMessage());

        List<ErrorResponse.ValidationError> validationErrors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(this::mapFieldError)
            .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .error("VALIDATION_ERROR")
            .message("Invalid input data")
            .details("One or more fields have validation errors")
            .path(request.getRequestURI())
            .validationErrors(validationErrors)
            .build();

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler({InvalidFormatException.class, HttpMessageNotReadableException.class})
    @ApiResponse(
        responseCode = "422",
        description = "Invalid JSON format or enum value",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<ErrorResponse> handleJsonParseException(
        Exception ex,
        HttpServletRequest request) {

        log.warn("JSON parsing error: {}", ex.getMessage());

        String message = "Invalid input format";
        String details = "The request contains invalid data format";

        // Handle specific Jackson InvalidFormatException for enum values
        if (ex instanceof HttpMessageNotReadableException httpEx &&
            httpEx.getCause() instanceof InvalidFormatException invalidFormatEx) {

            Class<?> targetType = invalidFormatEx.getTargetType();
            Object value = invalidFormatEx.getValue();

            if (targetType != null && targetType.isEnum()) {
                String enumName = targetType.getSimpleName();
                String[] validValues = Arrays.stream(targetType.getEnumConstants())
                    .map(Object::toString)
                    .toArray(String[]::new);

                message = String.format("Invalid %s value", enumName);
                details = String.format("Invalid value '%s' for %s. Valid values are: %s",
                    value, enumName, Arrays.toString(validValues));
            }
        } else if (ex instanceof InvalidFormatException invalidFormatEx) {
            Class<?> targetType = invalidFormatEx.getTargetType();
            Object value = invalidFormatEx.getValue();

            if (targetType != null && targetType.isEnum()) {
                String enumName = targetType.getSimpleName();
                String[] validValues = Arrays.stream(targetType.getEnumConstants())
                    .map(Object::toString)
                    .toArray(String[]::new);

                message = String.format("Invalid %s value", enumName);
                details = String.format("Invalid value '%s' for %s. Valid values are: %s",
                    value, enumName, Arrays.toString(validValues));
            }
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .error("INVALID_FORMAT")
            .message(message)
            .details(details)
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    @ApiResponse(
        responseCode = "404",
        description = "Resource not found",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<ErrorResponse> handleNotFoundException(
        NotFoundException ex,
        HttpServletRequest request) {

        log.warn("Resource not found: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("NOT_FOUND")
            .message("Resource not found")
            .details(ex.getMessage())
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserDomainException.class)
    @ApiResponse(
        responseCode = "409",
        description = "Business rule violation",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<ErrorResponse> handleUserDomainException(
        UserDomainException ex,
        HttpServletRequest request) {

        log.warn("Domain exception: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.CONFLICT.value())
            .error("BUSINESS_RULE_VIOLATION")
            .message("Business rule violation")
            .details(ex.getMessage())
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<ErrorResponse> handleGenericException(
        Exception ex,
        HttpServletRequest request) {

        log.error("Unexpected error: ", ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("INTERNAL_SERVER_ERROR")
            .message("An unexpected error occurred")
            .details("Please contact support if the problem persists")
            .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private ErrorResponse.ValidationError mapFieldError(FieldError fieldError) {
        return ErrorResponse.ValidationError.builder()
            .field(fieldError.getField())
            .rejectedValue(fieldError.getRejectedValue())
            .message(fieldError.getDefaultMessage())
            .build();
    }
} 