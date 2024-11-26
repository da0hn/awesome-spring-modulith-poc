package dev.da0hn.library.management.system.catalog;

import dev.da0hn.library.management.system.catalog.dto.CategoryDetail;
import dev.da0hn.library.management.system.catalog.dto.CreateCategoryInput;
import dev.da0hn.library.management.system.shared.dto.ApiCollectionResponse;
import dev.da0hn.library.management.system.shared.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(
        summary = "Cria uma nova categoria",
        description = "Cria uma nova categoria",
        tags = { "categories" }
    )
    public ResponseEntity<ApiResponse<CategoryDetail>> createCategory(@RequestBody final CreateCategoryInput dto) {
        final var category = this.categoryService.save(dto);

        return ResponseEntity.ok(ApiResponse.of(CategoryDetail.of(category)));
    }

    @GetMapping("/{categoryId}")
    @Operation(
        summary = "Busca uma categoria pelo id",
        description = "Busca uma categoria pelo id",
        tags = { "categories" }
    )
    public ResponseEntity<ApiResponse<CategoryDetail>> findById(@PathVariable final Long categoryId) {
        return this.categoryService.findById(categoryId)
            .map(category -> ResponseEntity.ok(ApiResponse.of(CategoryDetail.of(category))))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(
        summary = "Busca todas as categorias",
        description = "Busca todas as categorias",
        tags = { "categories" }
    )
    public ResponseEntity<ApiCollectionResponse<CategoryDetail>> findAll() {
        final var categories = this.categoryService.findAll().stream()
            .map(CategoryDetail::of)
            .toList();
        return ResponseEntity.ok(ApiCollectionResponse.of(categories));
    }

}
