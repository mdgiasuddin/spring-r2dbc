package org.example.springr2dbc.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    LOGGED_IN_INFORMATION_MISSING("No logged in information found!");

    private final String message;
}
