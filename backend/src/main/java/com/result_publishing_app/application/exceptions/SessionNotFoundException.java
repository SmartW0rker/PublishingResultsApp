package com.result_publishing_app.application.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String str) {
        super(str);
    }
}
