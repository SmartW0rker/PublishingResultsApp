package com.result_publishing_app.application.exceptions;

public class CourseNotExist extends RuntimeException{
    public CourseNotExist(String str) {
        super(str);
    }
}
