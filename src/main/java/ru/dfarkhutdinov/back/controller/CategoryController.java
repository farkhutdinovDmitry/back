package ru.dfarkhutdinov.back.controller;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.dfarkhutdinov.back.dto.ResponseDTO;
import ru.dfarkhutdinov.back.entity.Category;
import ru.dfarkhutdinov.back.exception.BadRequestException;
import ru.dfarkhutdinov.back.exception.CommonException;
import ru.dfarkhutdinov.back.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("categories/all")
    @ResponseBody
    public ResponseDTO<List<Category>> getCategories() {
        return ResponseDTO.createResponse(categoryService.getAllCategories());
    }

    @PostMapping("categories/add")
    public ResponseDTO<Category> addCategory(@RequestBody Category category) {
        val created = categoryService.addCategory(category);
        return ResponseDTO.createResponse(created);
    }

    @PostMapping("categories/update")
    public ResponseDTO<Category> updateCategory(@RequestBody Category category) {
        val updated = categoryService.updateCategory(category);
        return ResponseDTO.createResponse(updated);
    }

    @DeleteMapping("categories/delete")
    public ResponseDTO<Category> deleteCategory(@RequestParam String id) throws CommonException {
        try {
            val convertedId = Long.parseLong(id);
            Category deleted = categoryService.deleteCategoryById(convertedId);
            return ResponseDTO.createResponse(deleted);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("id must be a number");
        }
    }

    @GetMapping("categories")
    public ResponseDTO<Category> getCategoryByName(@RequestParam String name) {
        val category = categoryService.findCategoryByName(name);
        return ResponseDTO.createResponse(category);
    }

}

