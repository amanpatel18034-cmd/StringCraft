package com.stringcraft.stringcraft.dto;

public class StringRequest {
    private String text;
    private String mode; // For case conversion: "UPPER", "LOWER", "TOGGLE"

    public StringRequest() {
    }

    public StringRequest(String text) {
        this.text = text;
    }

    public StringRequest(String text, String mode) {
        this.text = text;
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

