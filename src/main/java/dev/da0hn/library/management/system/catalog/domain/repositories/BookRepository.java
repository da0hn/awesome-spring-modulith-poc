package dev.da0hn.library.management.system.catalog.domain.repositories;

import dev.da0hn.library.management.system.catalog.domain.Book;
import dev.da0hn.library.management.system.catalog.domain.exceptions.BookNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    default Book findByIdOrElseThrow(final Long bookId) {
        return this.findById(bookId)
            .orElseThrow(() -> BookNotFoundException.of(bookId));
    }

}
