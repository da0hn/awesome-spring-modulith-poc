package dev.da0hn.library.management.system.catalog.domain.repositories;

import dev.da0hn.library.management.system.catalog.domain.Author;
import dev.da0hn.library.management.system.catalog.domain.exceptions.AuthorNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    default Author findByIdOrElseThrow(final Long authorId) {
        return this.findById(authorId)
            .orElseThrow(() -> AuthorNotFoundException.of(authorId));
    }

}
