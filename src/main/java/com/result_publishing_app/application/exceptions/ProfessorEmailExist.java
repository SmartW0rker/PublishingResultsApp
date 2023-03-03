package com.result_publishing_app.application.exceptions;

import java.util.logging.Logger;

public class ProfessorEmailExist extends RuntimeException{
    public ProfessorEmailExist(String email) {
        super(String.format("Professor with email %s exist%n",email));
    }
}
