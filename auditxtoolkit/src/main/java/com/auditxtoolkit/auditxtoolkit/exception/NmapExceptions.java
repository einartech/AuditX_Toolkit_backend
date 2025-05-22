package com.auditxtoolkit.auditxtoolkit.exception;

public class NmapExceptions {

    public static class InvalidTargetException extends RuntimeException {
        public InvalidTargetException(String message) {
            super(message);
        }
    }

    public static class NmapExecutionException extends RuntimeException {
        public NmapExecutionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
