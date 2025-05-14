package com.auditxtoolkit.auditxtoolkit.dto;

public class NmapRequest {
    private String target;
    private String flags;

    public NmapRequest() {
    }

    public NmapRequest(String target, String flags) {
        this.target = target;
        this.flags = flags;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }
}
