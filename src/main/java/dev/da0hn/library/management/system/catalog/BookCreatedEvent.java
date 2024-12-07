package dev.da0hn.library.management.system.catalog;

import org.springframework.modulith.events.Externalized;

@Externalized("catalog.book.created")
public record BookCreatedEvent(Long id) {
}
