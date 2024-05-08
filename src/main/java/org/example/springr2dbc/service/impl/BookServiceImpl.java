package org.example.springr2dbc.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.dto.request.BookCreateRequest;
import org.example.springr2dbc.model.dto.response.BookResponse;
import org.example.springr2dbc.model.entity.Book;
import org.example.springr2dbc.repository.BookRepository;
import org.example.springr2dbc.service.BookService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Mono<BookResponse> createNewBook(BookCreateRequest request) {
        return bookRepository.save(new Book(request))
                .map(BookResponse::new);
    }

    @Override
    public Flux<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .map(BookResponse::new);
    }

    @Override
    public Mono<BookResponse> getBookById(int id) {
        return bookRepository.findById(id)
                .map(BookResponse::new);
    }
}
