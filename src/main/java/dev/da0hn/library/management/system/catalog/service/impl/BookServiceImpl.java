package dev.da0hn.library.management.system.catalog.service.impl;

import dev.da0hn.library.management.system.catalog.BookCreatedEvent;
import dev.da0hn.library.management.system.catalog.BookService;
import dev.da0hn.library.management.system.catalog.BookUpdatedEvent;
import dev.da0hn.library.management.system.catalog.domain.repositories.AuthorRepository;
import dev.da0hn.library.management.system.catalog.domain.Book;
import dev.da0hn.library.management.system.catalog.domain.repositories.BookRepository;
import dev.da0hn.library.management.system.catalog.domain.BookStatus;
import dev.da0hn.library.management.system.catalog.domain.repositories.CategoryRepository;
import dev.da0hn.library.management.system.catalog.dto.CreateBookInput;
import dev.da0hn.library.management.system.catalog.dto.UpdateBookInput;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    private final AuthorRepository authorRepository;

    @Override
    public Optional<Book> findById(final Long id) {
        log.info("Consultando o livro pelo id: {}", id);
        return this.bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book save(final CreateBookInput dto) {
        log.info("Salvando livro: {}", dto.title());

        final var newBook = Book.builder()
            .title(dto.title())
            .isbn(dto.isbn())
            .status(BookStatus.AVAILABLE)
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .category(this.categoryRepository.findByIdOrElseThrow(dto.categoryId()))
            .author(this.authorRepository.findByIdOrElseThrow(dto.authorId()))
            .build();

        this.bookRepository.save(newBook);

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(newBook.getId()));

        return newBook;
    }

    @Override
    @Transactional
    public Book update(final UpdateBookInput dto, final Long bookId) {
        log.info("Atualizando livro: {}", dto.title());

        final var book = this.bookRepository.findByIdOrElseThrow(bookId);

        final var bookUpdatedEvent = new BookUpdatedEvent(
            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getCategory().getId(),
            dto.title(),
            dto.isbn(),
            dto.categoryId()
        );

        book.changeTitle(dto.title());
        book.changeIsbn(dto.isbn());
        book.changeCategory(this.categoryRepository.findByIdOrElseThrow(dto.categoryId()));

        this.bookRepository.save(book);

        this.applicationEventPublisher.publishEvent(bookUpdatedEvent);

        return book;
    }

    @Override
    public List<Book> findAll() {
        log.info("Consultando todos os livros");
        return this.bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        log.info("Deletando livro pelo id: {}", id);
        this.bookRepository.deleteById(id);
    }

}
