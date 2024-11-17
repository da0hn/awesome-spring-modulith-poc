package dev.da0hn.library.management.system.catalog.domain.exceptions;

import java.io.Serial;

public class BookNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5852215848219426040L;

    private static final String DEFAULT_MESSAGE = "Livro com id %d não encontrado";

    private BookNotFoundException(final String message) {
        super(message);
    }

    private BookNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static BookNotFoundException of(final Long id) {
        return new BookNotFoundException(String.format(DEFAULT_MESSAGE, id));
    }

    public static BookNotFoundException of(final Long id, final Throwable cause) {
        return new BookNotFoundException(String.format(DEFAULT_MESSAGE, id), cause);
    }

}
