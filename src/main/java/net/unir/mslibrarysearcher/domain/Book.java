package net.unir.mslibrarysearcher.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String briefDescription;
    private String description;
    private String author;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "FK_book_category"))
    private Category category;
    private String isbn10;
    private String isbn13;
    private LocalDate publicationDate;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
}
