package com.gl.entity;

public enum FileType {

    Avatar("avatar"),

    File("file");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
