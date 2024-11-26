package dev.da0hn.library.management.system.catalog.dto;

import dev.da0hn.library.management.system.catalog.domain.Category;

public record CategoryDetail(
    Long id,
    String name
) {

    public static CategoryDetail of(final Category category) {
        return new CategoryDetail(category.getId(), category.getName());
    }

}
