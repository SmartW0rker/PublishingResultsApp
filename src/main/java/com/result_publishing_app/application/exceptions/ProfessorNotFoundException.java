package com.result_publishing_app.application.exceptions;

import java.util.function.Supplier;

public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(long id) {
        super(String.format("Professor with id %d does not exist",id));
    }
}
