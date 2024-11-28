package com.popov.transaction.dto;

import com.popov.transaction.constant.CategoryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response object for category details")
public class CategoryResponse {

    @Schema(description = "Unique identifier of the category", example = "1")
    private Long id;

    @Schema(description = "Name of the category", example = "Groceries")
    private String name;

    @Schema(description = "Type of the category (e.g., 'SYSTEM' or 'CUSTOM')", example = "CUSTOM")
    private CategoryType type;

}
