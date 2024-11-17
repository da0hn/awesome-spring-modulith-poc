package dev.da0hn.library.management.system.catalog;

import dev.da0hn.library.management.system.catalog.dto.BookDetail;
import dev.da0hn.library.management.system.catalog.dto.CreateBookInput;
import dev.da0hn.library.management.system.catalog.dto.UpdateBookInput;
import dev.da0hn.library.management.system.shared.dto.ApiCollectionResponse;
import dev.da0hn.library.management.system.shared.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookDetail>> createBook(@RequestBody final CreateBookInput input) {
        final var book = this.bookService.save(input);
        return ResponseEntity.ok(ApiResponse.of(BookDetail.of(book)));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookDetail>> findById(@PathVariable final Long bookId) {
        return this.bookService.findById(bookId)
            .map(book -> ResponseEntity.ok(ApiResponse.of(BookDetail.of(book))))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ApiCollectionResponse<BookDetail>> findAll() {
        final var books = this.bookService.findAll().stream()
            .map(BookDetail::of)
            .toList();
        return ResponseEntity.ok(ApiCollectionResponse.of(books));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookDetail>> updateBook(
        @RequestBody final UpdateBookInput input,
        @PathVariable final Long bookId
    ) {
        final var book = this.bookService.update(input, bookId);
        return ResponseEntity.ok(ApiResponse.of(BookDetail.of(book)));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable final Long bookId) {
        this.bookService.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }

}
