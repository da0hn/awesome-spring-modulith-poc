package dev.da0hn.library.management.system.catalog.service.impl;

import dev.da0hn.library.management.system.catalog.CategoryService;
import dev.da0hn.library.management.system.catalog.domain.Category;
import dev.da0hn.library.management.system.catalog.domain.exceptions.CategoryAlreadyExistsException;
import dev.da0hn.library.management.system.catalog.domain.repositories.CategoryRepository;
import dev.da0hn.library.management.system.catalog.dto.CreateCategoryInput;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category save(final CreateCategoryInput dto) {
        log.info("Criando categoria: {}", dto.name());

        if (this.categoryRepository.existsByName(dto.name())) {
            throw CategoryAlreadyExistsException.of(dto.name());
        }

        final var newCategory = Category.builder()
            .name(dto.name())
            .build();

        this.categoryRepository.save(newCategory);

        return newCategory;
    }

    @Override
    public List<Category> findAll() {
        log.info("Consultando todas as categorias");
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(final Long id) {
        log.info("Consultando categoria pelo id: {}", id);
        return this.categoryRepository.findById(id);
    }

}
