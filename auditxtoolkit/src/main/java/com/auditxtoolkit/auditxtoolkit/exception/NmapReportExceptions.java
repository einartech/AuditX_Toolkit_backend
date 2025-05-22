package com.auditxtoolkit.auditxtoolkit.exception;

public class NmapReportExceptions {

    public static class NmapReportNotFoundException extends RuntimeException {
        public NmapReportNotFoundException(Long id) {
            super("Nmap report not found with id: " + id);
        }

        public NmapReportNotFoundException(String message) {
            super(message);
        }
    }

    public static class NmapReportAlreadyExistsException extends RuntimeException {
        public NmapReportAlreadyExistsException(String message) {
            super(message);
        }
    }

    // Puedes agregar más excepciones específicas si lo necesitas
}
