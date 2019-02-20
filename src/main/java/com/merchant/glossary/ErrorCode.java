package com.merchant.glossary;

public enum  ErrorCode {
    TEXT_IS_NOT_ROMAN("is not roman");
    private String value;
    ErrorCode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
