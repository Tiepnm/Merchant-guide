package com.merchant.glossary;

public enum Roman {
    I("I", 1), V("V", 5), X("X", 10), L("L", 50), C("C", 100), D("D", 500), M("M", 1000);

    private int value;
    private String displayValue;

    Roman(String displayValue, int value) {
        this.displayValue = displayValue;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}

