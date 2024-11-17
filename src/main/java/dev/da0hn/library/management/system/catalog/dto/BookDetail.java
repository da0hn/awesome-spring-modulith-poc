package dev.da0hn.library.management.system.catalog.dto;

import dev.da0hn.library.management.system.catalog.domain.Book;
import dev.da0hn.library.management.system.catalog.domain.BookStatus;
import dev.da0hn.library.management.system.shared.dto.ResourceOption;
import dev.da0hn.library.management.system.shared.dto.SimpleResourceOption;
import lombok.Builder;

import java.time.Instant;

@Builder
public record BookDetail(
    Long id,
    String title,
    String isbn,
    BookStatus status,
    Instant createdAt,
    Instant updatedAt,
    ResourceOption category,
    ResourceOption author
) {

    public static BookDetail of(final Book book) {
        return BookDetail.builder()
            .id(book.getId())
            .title(book.getTitle())
            .isbn(book.getIsbn())
            .status(book.getStatus())
            .createdAt(book.getCreatedAt())
            .updatedAt(book.getUpdatedAt())
            .category(SimpleResourceOption.of(book.getCategory().getId(), book.getCategory().getName()))
            .author(SimpleResourceOption.of(book.getAuthor().getId(), book.getAuthor().getName()))
            .build();
    }

}
