package org.example.springr2dbc.exception;

import org.example.springr2dbc.constant.ExceptionConstant;

import java.util.Arrays;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(ExceptionConstant ex, String... params) {
        super(ex.name(), String.format(ex.getMessage(), Arrays.stream(params).toArray()));
    }

}
