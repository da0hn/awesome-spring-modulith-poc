package dev.da0hn.library.management.system.catalog;

import dev.da0hn.library.management.system.catalog.domain.Category;
import dev.da0hn.library.management.system.catalog.dto.CreateCategoryInput;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(CreateCategoryInput dto);

    List<Category> findAll();

    Optional<Category> findById(Long id);


}
