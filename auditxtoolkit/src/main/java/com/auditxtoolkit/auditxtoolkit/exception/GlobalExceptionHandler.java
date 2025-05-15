package com.auditxtoolkit.auditxtoolkit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExceptions.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserExceptions.UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserExceptions.EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailExists(UserExceptions.EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserExceptions.UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameExists(UserExceptions.UsernameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserExceptions.InvalidUserDataException.class)
    public ResponseEntity<String> handleInvalidUserData(UserExceptions.InvalidUserDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserExceptions.OperationNotAllowedException.class)
    public ResponseEntity<String> handleOperationNotAllowed(UserExceptions.OperationNotAllowedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder("Validation errors:\n");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            sb.append("- Field '")
                    .append(error.getField())
                    .append("': ")
                    .append(error.getDefaultMessage())
                    .append(" (rejected value: ")
                    .append(error.getRejectedValue())
                    .append(")\n");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
    }
}