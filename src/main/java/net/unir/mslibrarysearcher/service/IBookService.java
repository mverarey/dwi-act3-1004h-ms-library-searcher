package net.unir.mslibrarysearcher.service;

import net.unir.mslibrarysearcher.domain.BookRequest;
import net.unir.mslibrarysearcher.domain.BookResponse;

import java.util.List;

public interface IBookService {

    List<BookResponse> findAll();

    BookResponse findById(Long bookId);

    BookResponse save(BookRequest bookRequest);

    BookResponse update(Long bookId, BookRequest bookRequest);

    boolean deleteById(Long bookId);

    List<BookResponse> findByCategoryName(String categoryName);

    List<BookResponse> search(String text);

    BookResponse updatePatch(Long bookId, BookRequest bookRequest);
}
