package org.example.springr2dbc.controller;

import org.example.springr2dbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {BookController.class}, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class BookControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookService bookService;

    private final Random random = new SecureRandom();

    @Test
    void testGetAllBooks() {

        when(bookService.getAllBooks())
                .thenReturn(Flux.empty());

        webClient
                .get().uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @ParameterizedTest
    @MethodSource("getValidBookIds")
    void testGetBookByIdSucceeded(int id) {

        when(bookService.getBookById(anyInt()))
                .thenReturn(Mono.empty());

        webClient
                .get().uri("/api/books/" + id)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @ParameterizedTest
    @MethodSource("getInvalidBookIds")
    void testGetBookByIdValidationFailed(int id) {

        when(bookService.getBookById(anyInt()))
                .thenReturn(Mono.empty());

        webClient
                .get().uri("/api/books/" + id)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.PRECONDITION_FAILED);
    }

    private Stream<Arguments> getValidBookIds() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(1 + random.nextInt(10000)),
                Arguments.of(1 + random.nextInt(10000))
        );
    }

    private Stream<Arguments> getInvalidBookIds() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(random.nextInt(10000) - 10001),
                Arguments.of(random.nextInt(10000) - 10001)
        );
    }
}
