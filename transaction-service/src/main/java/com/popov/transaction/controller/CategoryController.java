package com.popov.transaction.controller;

import com.popov.transaction.dto.CategoryRequest;
import com.popov.transaction.dto.CategoryResponse;
import com.popov.transaction.entity.Category;
import com.popov.transaction.mapper.CategoryMapper;
import com.popov.transaction.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "API for managing categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(
            summary = "Retrieve a category by ID",
            description = "Fetches the details of a category by its unique identifier"
    )
    @GetMapping("/{id}")
    @PreAuthorize("@accessControl.canReadCategory(#id)")
    public CategoryResponse getCategoryById(@PathVariable @Parameter(description = "Category ID", example = "1") Long id) {
        Category category = categoryService.getCategory(id);
        return categoryMapper.toResponse(category);
    }

    @Operation(
            summary = "Create a new category",
            description = "Adds a new category to the system with the provided details"
    )
    @PostMapping
    @PreAuthorize("@accessControl.canCreateCategory()")
    public CategoryResponse createCategory(
            @RequestBody(description = "Details of the new category") CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryService.createCategory(category);
        return categoryMapper.toResponse(savedCategory);
    }

}
