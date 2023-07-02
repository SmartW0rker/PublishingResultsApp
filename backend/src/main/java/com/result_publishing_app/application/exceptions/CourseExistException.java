package com.result_publishing_app.application.exceptions;

public class CourseExistException extends RuntimeException {
    public CourseExistException(String str) {
        super(str);
    }
}
