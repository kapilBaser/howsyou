package com.kapil.howsyou.exception;

import java.util.List;

public class UserAlreadyExist extends RuntimeException{
    private String message;
    private List<String> details;

    public UserAlreadyExist(String message, List<String> details) {
        super(message);
        this.message = message;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }
}
