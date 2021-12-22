package com.example.demo.enumeration;

public enum MethodTypeEnum {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT");

    private String value;

    MethodTypeEnum(final String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
