package net.unir.mslibrarysearcher.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.unir.mslibrarysearcher.domain.Category;
import net.unir.mslibrarysearcher.domain.CategoryRequest;
import net.unir.mslibrarysearcher.domain.CategoryResponse;
import net.unir.mslibrarysearcher.repository.ICategoryRepository;
import net.unir.mslibrarysearcher.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> objectMapper.convertValue(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(category -> objectMapper.convertValue(category, CategoryResponse.class))
                .orElse(null);
    }

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = objectMapper.convertValue(categoryRequest, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return objectMapper.convertValue(savedCategory, CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(Long categoryId, CategoryRequest categoryRequest) {
        if (categoryRepository.existsById(categoryId)) {
            Category category = objectMapper.convertValue(categoryRequest, Category.class);
            category.setCategoryId(categoryId);
            Category savedCategory = categoryRepository.save(category);
            return objectMapper.convertValue(savedCategory, CategoryResponse.class);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }
}
