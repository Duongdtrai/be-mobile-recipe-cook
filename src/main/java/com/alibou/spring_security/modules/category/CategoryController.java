package com.alibou.spring_security.modules.category;

import com.alibou.spring_security.base.middleware.responses.ResponsePage;
import com.alibou.spring_security.base.middleware.responses.SystemResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@SecurityRequirement(name = "Authorization")
@Tag(name = "Categories", description = "Category APIs")
public class CategoryController {

  private final CategoryService recipeService;

  @PostMapping
  public SystemResponse<CategoryEntity> create(@RequestBody CategoryDataDto recipe) {
    try {
      CategoryEntity saved = recipeService.create(recipe);
      return new SystemResponse<>(200, "Recipe created", saved);
    } catch (Exception e) {
      return new SystemResponse<>(500, e.getMessage());
    }
  }

  @GetMapping
  public SystemResponse<ResponsePage<CategoryEntity>> findAll(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "10") int size) {
    Page<CategoryEntity> data = recipeService.getAll(page, size);
    return new SystemResponse<>(200, "SUCCESS", new ResponsePage<>(data));
  }

  @GetMapping("/{id}")
  public SystemResponse<CategoryEntity> getById(@PathVariable Long id) {
    CategoryEntity dto = recipeService.getById(id);
    return new SystemResponse<>(200, "SUCCESS", dto);
  }

  @PutMapping("/{id}")
  public SystemResponse<CategoryEntity> update(@PathVariable Long id, @RequestBody CategoryDataDto recipe) {
    CategoryEntity updated = recipeService.update(id, recipe);
    return new SystemResponse<>(200, "Updated", updated);
  }

  @DeleteMapping("/{id}")
  public SystemResponse<Void> delete(@PathVariable Long id) {
    recipeService.delete(id);
    return new SystemResponse<>(200, "Deleted");
  }
}
