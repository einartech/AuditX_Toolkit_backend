package com.auditxtoolkit.auditxtoolkit.exception;

public class UserExceptions {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Integer id) {
            super(ANSI_RED + "User not found with id: " + id + ANSI_RESET);
        }

        public UserNotFoundException(String message) {
            super(ANSI_RED + message + ANSI_RESET);
        }
    }

    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String email) {
            super(ANSI_RED + "Email already exists: " + email + ANSI_RESET);
        }
    }

    public static class UsernameAlreadyExistsException extends RuntimeException {
        public UsernameAlreadyExistsException(String username) {
            super(ANSI_RED + "Username already exists: " + username + ANSI_RESET);
        }
    }

    public static class InvalidUserDataException extends RuntimeException {
        public InvalidUserDataException(String message) {
            super(ANSI_RED + "Invalid user data: " + message + ANSI_RESET);
        }
    }

    public static class OperationNotAllowedException extends RuntimeException {
        public OperationNotAllowedException(String message) {
            super(ANSI_RED + "Operation not allowed: " + message + ANSI_RESET);
        }
    }
}