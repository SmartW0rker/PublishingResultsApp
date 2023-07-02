package com.result_publishing_app.application.exceptions;

public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(String id) {
        super(String.format("Professor with id %s does not exist",id));
    }
}
