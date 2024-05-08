package org.example.springr2dbc.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springr2dbc.model.entity.Book;

import java.time.LocalDate;

import static org.example.springr2dbc.constant.AppConstant.DATE_PATTERN;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponse {
    private int id;
    private Boolean available;
    private String name;
    private Double price;
    @JsonFormat(pattern = DATE_PATTERN)
    private LocalDate publishedDate;
    private String authorName;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.available = book.getAvailable();
        this.name = book.getName();
        this.price = book.getPrice();
        this.publishedDate = book.getPublishedDate();
        this.authorName = book.getAuthorName();
    }
}
