package dev.da0hn.library.management.system.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;

public record CreateBookInput(
    @NotBlank
    @NotNull
    String title,
    @ISBN
    @NotNull
    String isbn,
    @NotNull
    Long categoryId,
    @NotNull
    Long authorId
) {
}
