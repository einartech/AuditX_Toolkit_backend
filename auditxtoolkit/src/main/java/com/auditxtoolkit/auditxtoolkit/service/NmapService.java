package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.dto.NmapRequest;
import com.auditxtoolkit.auditxtoolkit.dto.NmapResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class NmapService {

    public NmapResponse runNmapScan(NmapRequest request) {
        StringBuilder output = new StringBuilder();
        int exitCode = -1;
        try {
            // Split flags by space for ProcessBuilder
            String[] flags = request.getFlags() != null && !request.getFlags().isEmpty()
                    ? request.getFlags().split("\\s+")
                    : new String[0];
            String[] command = new String[flags.length + 2];
            command[0] = "nmap";
            System.arraycopy(flags, 0, command, 1, flags.length);
            command[flags.length + 1] = request.getTarget();

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
            output.append("Error running nmap: ").append(e.getMessage());
        }
        return new NmapResponse(output.toString(), exitCode);
    }
}
