package com.auditxtoolkit.auditxtoolkit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
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
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "Field '" + error.getField() + "': " + error.getDefaultMessage() +
                        " (rejected value: " + error.getRejectedValue() + ")")
                .collect(Collectors.toList());

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Validation errors");
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NmapReportExceptions.NmapReportNotFoundException.class)
    public ResponseEntity<String> handleNmapReportNotFound(NmapReportExceptions.NmapReportNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NmapReportExceptions.NmapReportAlreadyExistsException.class)
    public ResponseEntity<String> handleNmapReportAlreadyExists(
            NmapReportExceptions.NmapReportAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NmapExceptions.InvalidTargetException.class)
    public ResponseEntity<String> handleInvalidTarget(NmapExceptions.InvalidTargetException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NmapExceptions.NmapExecutionException.class)
    public ResponseEntity<String> handleNmapExecution(NmapExceptions.NmapExecutionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}