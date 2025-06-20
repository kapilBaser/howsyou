package com.kapil.howsyou.exception;

import java.util.List;

public class CustomExceptionFormat {
    private String message;
    private List<String> details;

    public CustomExceptionFormat(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
