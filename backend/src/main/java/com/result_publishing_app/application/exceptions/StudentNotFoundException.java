package com.result_publishing_app.application.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String str) {
        super(str);
    }
}
