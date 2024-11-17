package dev.da0hn.library.management.system.catalog.domain.repositories;

import dev.da0hn.library.management.system.catalog.domain.Category;
import dev.da0hn.library.management.system.catalog.domain.exceptions.CategoryNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    default Category findByIdOrElseThrow(final Long categoryId) {
        return this.findById(categoryId)
            .orElseThrow(() -> CategoryNotFoundException.of(categoryId));
    }

}
