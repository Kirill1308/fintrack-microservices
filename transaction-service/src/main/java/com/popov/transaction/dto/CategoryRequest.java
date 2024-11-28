package com.popov.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for creating a category")
public class CategoryRequest {

    @NotNull(message = "Name is required")
    @Schema(description = "Name of the category", example = "Groceries")
    private String name;

}
