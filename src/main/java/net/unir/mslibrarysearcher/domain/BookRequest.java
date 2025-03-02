package net.unir.mslibrarysearcher.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    private Long bookId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String briefDescription;
    @NotEmpty
    private String description;
    @NotEmpty
    private String author;
    private Double price;
    private CategoryRequest category;
    @NotEmpty
    private String isbn10;
    @NotEmpty
    private String isbn13;
    private String publicationDate;
    @NotEmpty
    private String imageUrl;
    private BookStatus status;
}
