package net.unir.mslibrarysearcher.controller;

import jakarta.validation.Valid;
import net.unir.mslibrarysearcher.domain.CategoryRequest;
import net.unir.mslibrarysearcher.domain.CategoryResponse;
import net.unir.mslibrarysearcher.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.save(categoryRequest);
        if (Objects.nonNull(categoryResponse))
            return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.update(categoryId, categoryRequest);
        if (Objects.nonNull(categoryResponse))
            return ResponseEntity.ok(categoryResponse);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long categoryId) {
        boolean deleted = categoryService.deleteById(categoryId);
        if (deleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
