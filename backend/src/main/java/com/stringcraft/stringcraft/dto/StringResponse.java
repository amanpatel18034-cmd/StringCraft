package com.stringcraft.stringcraft.dto;

public class StringResponse {
    private boolean success;
    private String operation;
    private Object result;
    private String message;

    public StringResponse() {
    }

    public StringResponse(boolean success, String operation, Object result, String message) {
        this.success = success;
        this.operation = operation;
        this.result = result;
        this.message = message;
    }

    public static StringResponse ok(String operation, Object result) {
        return new StringResponse(true, operation, result, "Operation completed successfully");
    }

    public static StringResponse error(String message) {
        return new StringResponse(false, "ERROR", null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

