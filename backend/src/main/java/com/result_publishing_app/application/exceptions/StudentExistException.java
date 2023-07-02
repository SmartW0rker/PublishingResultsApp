package com.result_publishing_app.application.exceptions;

public class StudentExistException extends RuntimeException {
    public StudentExistException(String str) {
        super(str);
    }
}
