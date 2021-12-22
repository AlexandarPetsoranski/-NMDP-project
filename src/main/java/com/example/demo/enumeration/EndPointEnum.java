package com.example.demo.enumeration;

public enum EndPointEnum {
    SINGLE_POST("/posts/%s"),
    ALL_POSTS("/posts"),
    SINGLE_TODO("/todos/%s"),
    ALL_TODOS("/todos");

    private String value;

    EndPointEnum(final String value) {
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
