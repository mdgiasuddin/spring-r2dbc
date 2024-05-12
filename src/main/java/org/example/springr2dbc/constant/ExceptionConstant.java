package org.example.springr2dbc.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    ENTITY_NOT_FOUND("No %s found by %s: %s");

    private final String message;
}
