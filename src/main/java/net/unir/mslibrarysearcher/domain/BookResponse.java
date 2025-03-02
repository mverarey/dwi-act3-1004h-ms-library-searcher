package net.unir.mslibrarysearcher.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {

    private Long bookId;
    private String title;
    private String briefDescription;
    private String description;
    private String author;
    private Double price;
    private CategoryResponse category;
    private String isbn10;
    private String isbn13;
    private String publicationDate;
    private String imageUrl;
    private BookStatus status;
}
