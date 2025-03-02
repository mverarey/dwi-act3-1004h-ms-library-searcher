package net.unir.mslibrarysearcher.service;

import net.unir.mslibrarysearcher.domain.CategoryRequest;
import net.unir.mslibrarysearcher.domain.CategoryResponse;

import java.util.List;

public interface ICategoryService {

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long categoryId);

    CategoryResponse save(CategoryRequest categoryRequest);

    CategoryResponse update(Long categoryId, CategoryRequest categoryRequest);

    boolean deleteById(Long categoryId);
}
