package dev.da0hn.library.management.system.catalog.domain.exceptions;

import java.io.Serial;

public class AuthorNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5852215848219426040L;

    private static final String DEFAULT_MESSAGE = "Autor com id %d não encontrado";

    private AuthorNotFoundException(final String message) {
        super(message);
    }

    private AuthorNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static AuthorNotFoundException of(final Long id) {
        return new AuthorNotFoundException(String.format(DEFAULT_MESSAGE, id));
    }

    public static AuthorNotFoundException of(final Long id, final Throwable cause) {
        return new AuthorNotFoundException(String.format(DEFAULT_MESSAGE, id), cause);
    }

}
