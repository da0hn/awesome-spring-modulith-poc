package dev.da0hn.library.management.system.catalog.domain.repositories;

import dev.da0hn.library.management.system.catalog.domain.Category;
import dev.da0hn.library.management.system.catalog.domain.exceptions.CategoryNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    default Category findByIdOrElseThrow(final Long categoryId) {
        return this.findById(categoryId)
            .orElseThrow(() -> CategoryNotFoundException.of(categoryId));
    }

    @Query("select (count(c) > 0) from Category c where c.name = ?1")
    boolean existsByName(String name);

}
