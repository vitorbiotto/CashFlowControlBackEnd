package com.example.CashFlowControlBackEnd.Controller;

import com.example.CashFlowControlBackEnd.Entity.Category;
import com.example.CashFlowControlBackEnd.Exceptions.GenericException;
import com.example.CashFlowControlBackEnd.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public Category save(@RequestBody Category category) throws GenericException {
        return categoryService.save(category);
    }

    @PutMapping("/update/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) throws GenericException {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws GenericException {
        categoryService.delete(id);
    }
}
