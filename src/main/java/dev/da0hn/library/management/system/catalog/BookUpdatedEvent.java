package dev.da0hn.library.management.system.catalog;

public record BookUpdatedEvent(
    Long id,
    String oldTitle,
    String oldIsbn,
    Long oldCategoryId,
    String newTitle,
    String newIsbn,
    Long newCategoryId
) {
}
