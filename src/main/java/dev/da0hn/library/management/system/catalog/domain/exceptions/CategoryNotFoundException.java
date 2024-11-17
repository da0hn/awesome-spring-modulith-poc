package dev.da0hn.library.management.system.catalog.domain.exceptions;

import java.io.Serial;

public class CategoryNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5852215848219426040L;

    private static final String DEFAULT_MESSAGE = "Categoria com id %d não encontrada";

    private CategoryNotFoundException(final String message) {
        super(message);
    }

    private CategoryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static CategoryNotFoundException of(final Long id) {
        return new CategoryNotFoundException(String.format(DEFAULT_MESSAGE, id));
    }

    public static CategoryNotFoundException of(final Long id, final Throwable cause) {
        return new CategoryNotFoundException(String.format(DEFAULT_MESSAGE, id), cause);
    }

}
