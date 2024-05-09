package org.example.springr2dbc.exception;

import org.example.springr2dbc.constant.ExceptionConstant;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }

}
