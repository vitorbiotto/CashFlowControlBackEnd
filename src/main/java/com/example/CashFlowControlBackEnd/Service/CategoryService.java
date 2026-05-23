package com.example.CashFlowControlBackEnd.Service;

import com.example.CashFlowControlBackEnd.Entity.Category;
import com.example.CashFlowControlBackEnd.Exceptions.Enums.GenericExceptionKey;
import com.example.CashFlowControlBackEnd.Exceptions.GenericException;
import com.example.CashFlowControlBackEnd.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) throws GenericException {
        validateCategoryName(category.getName(), null);
        return categoryRepository.save(category);
    }

    public Category update(Long categoryId, Category category) throws GenericException {
        Category newCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new GenericException(GenericExceptionKey.CATEGORY_NOT_FOUND));

        validateCategoryName(category.getName(), categoryId);
        newCategory.setName(category.getName());

        return categoryRepository.save(newCategory);
    }

    public void delete(Long id) throws GenericException {
        validateIfIdExists(id);
        categoryRepository.deleteById(id);
    }

    private void validateIfIdExists(Long id) throws GenericException {
        if (id == null) {
            throw new GenericException(GenericExceptionKey.CATEGORY_ID_IS_REQUIRED);
        }

        if (!categoryRepository.existsById(id)) {
            throw new GenericException(GenericExceptionKey.CATEGORY_ID_NOT_FOUND);
        }
    }

    private void validateCategoryName(String name, Long categoryId) throws GenericException {
        if (name == null || name.isBlank()) {
            throw new GenericException(GenericExceptionKey.CATEGORY_NAME_IS_REQUIRED);
        }

        boolean duplicated = categoryId == null
                ? categoryRepository.existsByNameIgnoreCase(name)
                : categoryRepository.existsByNameIgnoreCaseAndIdNot(name, categoryId);

        if (duplicated) {
            throw new GenericException(GenericExceptionKey.CATEGORY_NAME_ALREADY_EXISTS);
        }
    }
}

