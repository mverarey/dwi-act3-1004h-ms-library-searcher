package net.unir.mslibrarysearcher.controller;

import jakarta.validation.Valid;
import net.unir.mslibrarysearcher.domain.BookRequest;
import net.unir.mslibrarysearcher.domain.BookResponse;
import net.unir.mslibrarysearcher.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(@RequestParam(value = "categoryName", required = false, defaultValue = "") String categoryName) {
        if (categoryName.isEmpty())
            return ResponseEntity.ok(bookService.findAll());
        else
            return ResponseEntity.ok(bookService.findByCategoryName(categoryName));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> search(@RequestParam(value = "text", required = false, defaultValue = "") String text) {
        if (text.isEmpty())
            return ResponseEntity.ok(bookService.findAll());
        else
                return ResponseEntity.ok(bookService.search(text));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @PostMapping
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.save(bookRequest);
        if (Objects.nonNull(bookResponse))
            return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> update(@PathVariable Long bookId, @Valid @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.update(bookId, bookRequest);
        if (Objects.nonNull(bookResponse))
            return ResponseEntity.ok(bookResponse);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{bookId}/patch")
    public ResponseEntity<BookResponse> updatePatch(@PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.updatePatch(bookId, bookRequest);
        if (Objects.nonNull(bookResponse))
            return ResponseEntity.ok(bookResponse);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long bookId) {
        boolean deleted = bookService.deleteById(bookId);
        if (deleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
