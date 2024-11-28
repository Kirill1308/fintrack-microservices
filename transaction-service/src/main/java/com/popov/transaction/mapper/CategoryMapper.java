package com.popov.transaction.mapper;

import com.popov.transaction.dto.CategoryRequest;
import com.popov.transaction.dto.CategoryResponse;
import com.popov.transaction.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category entity);

}
