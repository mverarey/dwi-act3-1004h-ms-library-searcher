package net.unir.mslibrarysearcher.repository;

import net.unir.mslibrarysearcher.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {

    @Query("FROM Book b WHERE b.category.categoryName LIKE %:categoryName%")
    List<Book> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("FROM Book b WHERE " +
            "LOWER(b.title) LIKE %:text% OR " +
            "LOWER(b.briefDescription) LIKE %:text% OR " +
            "LOWER(b.description) LIKE %:text% OR " +
            "LOWER(b.author) LIKE %:text% OR " +
            "LOWER(b.category.categoryName) LIKE %:text% OR " +
            "LOWER(b.isbn10) LIKE %:text% OR " +
            "LOWER(b.isbn13) LIKE %:text%")
    List<Book> search(@Param("text") String text);

    @Query("FROM Book b WHERE " +
            "LOWER(b.category.categoryName) LIKE %:text% ")
    List<Book> searchByCategory(@Param("text") String text);
}
