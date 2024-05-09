package org.example.springr2dbc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.dto.request.BookCreateRequest;
import org.example.springr2dbc.model.dto.response.BookResponse;
import org.example.springr2dbc.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Mono<BookResponse> createNewBook(@RequestBody BookCreateRequest request) {
        return bookService.createNewBook(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Flux<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public Mono<BookResponse> getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
}
