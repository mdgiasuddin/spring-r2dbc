package org.example.springr2dbc.exception;

public record ExceptionResponse(
    String code,
    String message
) {
}
