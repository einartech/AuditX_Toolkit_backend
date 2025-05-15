package com.auditxtoolkit.auditxtoolkit.dto.response;

public class NmapResponseDTO {
    private String output;
    private int exitCode;

    public NmapResponseDTO() {
    }

    public NmapResponseDTO(String output, int exitCode) {
        this.output = output;
        this.exitCode = exitCode;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }
}
