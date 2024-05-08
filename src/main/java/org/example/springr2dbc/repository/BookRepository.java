package org.example.springr2dbc.repository;

import org.example.springr2dbc.model.entity.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BookRepository extends R2dbcRepository<Book, Integer> {
}
