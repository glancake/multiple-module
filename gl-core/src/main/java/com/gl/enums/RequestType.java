package com.gl.enums;

public enum RequestType {

    JOIN("join"),
    LEAVE("leave");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static RequestType fromString(String type) {
        for (RequestType requestType : RequestType.values()) {
            if (requestType.type.equalsIgnoreCase(type)) {
                return requestType;
            }
        }
        throw new IllegalArgumentException("Invalid request type: " + type);
    }

}
