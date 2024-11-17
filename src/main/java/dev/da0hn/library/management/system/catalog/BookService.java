package dev.da0hn.library.management.system.catalog;

import dev.da0hn.library.management.system.catalog.domain.Book;
import dev.da0hn.library.management.system.catalog.dto.CreateBookInput;
import dev.da0hn.library.management.system.catalog.dto.UpdateBookInput;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);

    @Transactional
    Book save(CreateBookInput dto);

    @Transactional
    Book update(UpdateBookInput dto, Long bookId);

    List<Book> findAll();

    void deleteById(Long id);

}
