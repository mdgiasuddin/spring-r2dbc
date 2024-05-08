package org.example.springr2dbc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springr2dbc.model.dto.request.BookCreateRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    private int id;

    private Boolean available;
    private String name;
    private Double price;
    private LocalDate publishedDate;
    private String authorName;

    public Book(BookCreateRequest request) {
        this.available = request.available();
        this.name = request.name();
        this.price = request.price();
        this.publishedDate = request.publishedDate();
        this.authorName = request.authorName();
    }
}
