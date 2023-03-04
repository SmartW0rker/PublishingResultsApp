package com.result_publishing_app.application.exceptions;

public class SessionExistException extends RuntimeException {
    public SessionExistException(String str) {
        super(str);
    }
}
