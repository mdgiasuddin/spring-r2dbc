package org.example.springr2dbc.service;

import org.example.springr2dbc.model.dto.request.BookCreateRequest;
import org.example.springr2dbc.model.dto.response.BookResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<BookResponse> createNewBook(BookCreateRequest request);

    Flux<BookResponse> getAllBooks();

    Mono<BookResponse> getBookById(int id);
}
