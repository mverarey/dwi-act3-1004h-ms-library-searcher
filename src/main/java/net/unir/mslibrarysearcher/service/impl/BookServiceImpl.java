package net.unir.mslibrarysearcher.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.unir.mslibrarysearcher.domain.Book;
import net.unir.mslibrarysearcher.domain.BookRequest;
import net.unir.mslibrarysearcher.domain.BookResponse;
import net.unir.mslibrarysearcher.repository.IBookRepository;
import net.unir.mslibrarysearcher.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> objectMapper.convertValue(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse findById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> objectMapper.convertValue(book, BookResponse.class))
                .orElse(null);
    }

    @Override
    public BookResponse save(BookRequest bookRequest) {
        Book book = objectMapper.convertValue(bookRequest, Book.class);
        Book savedBook = bookRepository.save(book);
        return objectMapper.convertValue(savedBook, BookResponse.class);
    }

    @Override
    public BookResponse update(Long bookId, BookRequest bookRequest) {
        if (bookRepository.existsById(bookId)) {
            Book book = objectMapper.convertValue(bookRequest, Book.class);
            book.setBookId(bookId);
            Book savedBook = bookRepository.save(book);
            return objectMapper.convertValue(savedBook, BookResponse.class);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @Override
    public List<BookResponse> findByCategoryName(String categoryName) {
        return bookRepository.findByCategoryName(categoryName).stream()
                .map(book -> objectMapper.convertValue(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> search(String text) {
        if(text.contains("category:")) {
             // Extraer el nombre de la categoría eliminando "category:"
            String category = text.split("category:")[1].trim(); // Split y limpieza de espacios
            return bookRepository.searchByCategory(category.toLowerCase()).stream()
                .map(book -> objectMapper.convertValue(book, BookResponse.class))
                .collect(Collectors.toList());
        }
        else
        return bookRepository.search(text.toLowerCase()).stream()
                .map(book -> objectMapper.convertValue(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse updatePatch(Long bookId, BookRequest bookRequest) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(book)) {
            if (Objects.nonNull(bookRequest.getStatus()))
                book.setStatus(bookRequest.getStatus());
            Book savedBook = bookRepository.save(book);
            return objectMapper.convertValue(savedBook, BookResponse.class);
        }
        return null;
    }
}
