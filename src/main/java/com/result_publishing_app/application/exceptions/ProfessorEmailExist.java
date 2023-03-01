package com.result_publishing_app.application.exceptions;

import java.util.logging.Logger;

public class ProfessorEmailExist extends RuntimeException{
    public ProfessorEmailExist(String email) {
        super(String.format("Professor with %s exist%n",email));
        System.out.printf("Professor with %s exist%n",email);
    }
}
