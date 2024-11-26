package dev.da0hn.library.management.system.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryInput(
    @NotNull
    @NotBlank
    String name
) {
}
