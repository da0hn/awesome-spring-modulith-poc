package dev.da0hn.library.management.system.catalog;

import org.springframework.modulith.events.Externalized;

@Externalized("catalog.book.updated")
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
