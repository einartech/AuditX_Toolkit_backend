package com.auditxtoolkit.auditxtoolkit.dto.response;

public class NmapResponseDTO {
    private String command;
    private String output;
    private int exitCode;

    public NmapResponseDTO(String command, String output, int exitCode) {
        this.command = command;
        this.output = output;
        this.exitCode = exitCode;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
