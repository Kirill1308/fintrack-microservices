package com.popov.transaction.service;

import com.popov.exception.custom.CategoryNotFoundException;
import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.transaction.constant.CategoryType;
import com.popov.transaction.entity.Category;
import com.popov.transaction.repository.CategoryRepository;
import com.popov.transaction.utils.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;

    @Transactional
    public Category createCategory(Category category) {
        UserContext userContext = UserContextHolder.getUserContext();

        category.setUserId(userContext.getUserId());

        if (userContext.isAdmin()) {
            category.setType(CategoryType.SYSTEM);
        } else {
            category.setType(CategoryType.CUSTOM);
        }

        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long categoryId) {
        var userId = UserContextHolder.getUserContext().getUserId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        categoryValidator.validateCategoryAccess(category, userId);

        return category;
    }

    @Transactional(readOnly = true)
    public boolean isCategoryOwner(Long id, Long userId) {
        return categoryRepository.existsByIdAndUserId(id, userId);
    }

}
