package org.example.springr2dbc.service;

import org.example.springr2dbc.model.dto.response.BookResponse;
import org.example.springr2dbc.model.entity.Book;
import org.example.springr2dbc.repository.BookRepository;
import org.example.springr2dbc.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Flux;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {

    private BookServiceImpl bookService;
    private BookRepository bookRepository;

    private Random random;

    @BeforeAll
    public void setup() {
        random = new SecureRandom();
        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @ParameterizedTest
    @MethodSource("getAllBooks")
    void testGetAllBooks(Flux<Book> books) {
        when(bookRepository.findAll()).thenReturn(books);
        Flux<BookResponse> allBooks = bookService.getAllBooks();
    }


    private Stream<Arguments> getAllBooks() {
        return Stream.of(
                Arguments.of(Flux.just(createBookObject(), createBookObject(), createBookObject())),
                Arguments.of(Flux.just(createBookObject(), createBookObject(), createBookObject())),
                Arguments.of(Flux.just(createBookObject(), createBookObject(), createBookObject()))
        );
    }

    private Book createBookObject() {
        return new Book(
                1 + random.nextInt(10000),
                random.nextBoolean(),
                UUID.randomUUID().toString(),
                random.nextDouble(1000),
                LocalDate.now().minusDays(random.nextInt(1000)),
                UUID.randomUUID().toString()
        );
    }
}
