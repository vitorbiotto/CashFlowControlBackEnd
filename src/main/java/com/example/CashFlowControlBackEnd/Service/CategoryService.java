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
        validateFields(category);
        return categoryRepository.save(category);
    }

    private void validateFields(Category category) throws GenericException {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new GenericException(GenericExceptionKey.CATEGORY_NAME_IS_REQUIRED);
        }

        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new GenericException(GenericExceptionKey.CATEGORY_NAME_ALREADY_EXISTS);
        }
    }
}

