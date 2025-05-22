package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.dto.request.NmapRequestDTO;
import com.auditxtoolkit.auditxtoolkit.dto.response.NmapResponseDTO;
import com.auditxtoolkit.auditxtoolkit.exception.NmapExceptions;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

@Service
public class NmapService {

    private static final Logger logger = LoggerFactory.getLogger(NmapService.class);
    private static final Pattern IP_OR_HOST_PATTERN = Pattern.compile("^[a-zA-Z0-9.\\-]+$");

    public NmapResponseDTO runNmapScan(NmapRequestDTO request) {
        StringBuilder output = new StringBuilder();
        int exitCode = -1;
        String commandString = "";

        // Validación básica de target
        if (request.getTarget() == null || !IP_OR_HOST_PATTERN.matcher(request.getTarget()).matches()) {
            throw new NmapExceptions.InvalidTargetException(
                    "Invalid target: Only IP addresses or hostnames are allowed.");
        }

        try {
            // Sanitiza flags (solo permite flags seguros, puedes mejorar esta lista)
            String[] flags = request.getFlags() != null && !request.getFlags().isEmpty()
                    ? request.getFlags().split("\\s+")
                    : new String[0];

            // Construye el comando
            String[] command = new String[flags.length + 2];
            command[0] = "nmap";
            System.arraycopy(flags, 0, command, 1, flags.length);
            command[flags.length + 1] = request.getTarget();

            // Guarda el comando como string para devolverlo
            commandString = String.join(" ", command);

            logger.info("Executing Nmap command: {}", commandString);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            exitCode = process.waitFor();
        } catch (Exception e) {
            logger.error("Error running nmap", e);
            throw new NmapExceptions.NmapExecutionException("Error running nmap: " + e.getMessage(), e);
        }
        return new NmapResponseDTO(commandString, output.toString(), exitCode);
    }
}
