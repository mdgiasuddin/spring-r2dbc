package org.example.springr2dbc.model.dto.request;

import java.time.LocalDate;

public record BookCreateRequest(
        Boolean available,
        String name,
        Double price,
        LocalDate publishedDate,
        String authorName
) {
}
