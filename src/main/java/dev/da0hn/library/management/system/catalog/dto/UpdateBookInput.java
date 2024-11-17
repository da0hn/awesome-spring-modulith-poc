package dev.da0hn.library.management.system.catalog.dto;

public record UpdateBookInput(
    String title,
    String isbn,
    Long categoryId
) {
}
