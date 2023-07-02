package com.result_publishing_app.application.exceptions;

public class ProfessorExistException extends RuntimeException{
    public ProfessorExistException(String email) {
        super(String.format("Professor with email %s exist%n",email));
    }
}
