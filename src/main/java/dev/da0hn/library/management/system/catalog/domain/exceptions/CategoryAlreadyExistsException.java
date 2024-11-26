package dev.da0hn.library.management.system.catalog.domain.exceptions;

import java.io.Serial;

public class CategoryAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5852215848219426040L;

    private static final String DEFAULT_MESSAGE = "A categoria %s já existe";

    private CategoryAlreadyExistsException(final String message) {
        super(message);
    }

    private CategoryAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static CategoryAlreadyExistsException of(final String name) {
        return new CategoryAlreadyExistsException(String.format(DEFAULT_MESSAGE, name));
    }

    public static CategoryAlreadyExistsException of(final String name, final Throwable cause) {
        return new CategoryAlreadyExistsException(String.format(DEFAULT_MESSAGE, name), cause);
    }

}
