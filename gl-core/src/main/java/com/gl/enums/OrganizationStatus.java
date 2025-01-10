package com.gl.enums;

public enum OrganizationStatus {
    ACTIVE("active"),
    INACTIVE("inactive"),
    PENDING("pending");

    private final String status;

    OrganizationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrganizationStatus fromString(String text) {
        for (OrganizationStatus status : OrganizationStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
