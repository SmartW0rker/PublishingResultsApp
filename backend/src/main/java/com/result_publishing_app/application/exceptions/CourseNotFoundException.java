package com.result_publishing_app.application.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String str) {
        super(str);
    }
}
