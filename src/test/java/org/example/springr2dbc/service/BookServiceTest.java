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
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetAllBooks(Flux<Book> bookFlux, List<Book> books) {
        when(bookRepository.findAll()).thenReturn(bookFlux);
        Flux<BookResponse> bookResponseFlux = bookService.getAllBooks();
        Mono<List<BookResponse>> listMono = bookResponseFlux.collectList();

        listMono.subscribe(
                result -> {
                    assertEquals(result.size(), books.size());
                    assertEquals(result.getFirst().getId(), books.getFirst().getId());
                    assertEquals(result.getFirst().getAvailable(), books.getFirst().getAvailable());
                    assertEquals(result.getFirst().getName(), books.getFirst().getName());
                    assertEquals(result.getFirst().getPrice(), books.getFirst().getPrice());
                    assertEquals(result.getFirst().getPublishedDate(), books.getFirst().getPublishedDate());
                    assertEquals(result.getFirst().getAuthorName(), books.getFirst().getAuthorName());
                }
        );
    }


    private Stream<Arguments> getAllBooks() {
        List<Book> bookList1 = Arrays.asList(createBookObject(), createBookObject());
        List<Book> bookList2 = Arrays.asList(createBookObject(), createBookObject());
        List<Book> bookList3 = Arrays.asList(createBookObject(), createBookObject());

        return Stream.of(
                Arguments.of(Flux.just(bookList1.getFirst(), bookList1.get(1)), bookList1),
                Arguments.of(Flux.just(bookList2.getFirst(), bookList2.get(1)), bookList2),
                Arguments.of(Flux.just(bookList3.getFirst(), bookList3.get(1)), bookList3)
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
