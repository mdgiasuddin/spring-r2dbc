package org.example.springr2dbc.exception;

import org.example.springr2dbc.constant.ExceptionConstant;

public class RuleViolationException extends ApplicationException {

    public RuleViolationException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }
}
