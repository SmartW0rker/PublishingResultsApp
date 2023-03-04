package com.result_publishing_app.application.exceptions;

public class SubjectExistException extends RuntimeException {
    public SubjectExistException(String str) {
        super(str);
    }
}
