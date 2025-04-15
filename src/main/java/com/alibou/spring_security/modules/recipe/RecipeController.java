package com.alibou.spring_security.modules.recipe;

import com.alibou.spring_security.base.middleware.responses.ResponsePage;
import com.alibou.spring_security.base.middleware.responses.SystemResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/recipes")
@AllArgsConstructor
@SecurityRequirement(name = "Authorization")
@Tag(name = "Recipes", description = "Recipe APIs")
public class RecipeController {

  private final RecipeService recipeService;

  @PostMapping
  public SystemResponse<RecipeEntity> create(@RequestBody RecipeDataDto recipe) {
    try {
      RecipeEntity saved = recipeService.create(recipe);
      return new SystemResponse<>(200, "Recipe created", saved);
    } catch (Exception e) {
      return new SystemResponse<>(500, e.getMessage());
    }
  }

  @GetMapping
  public SystemResponse<ResponsePage<RecipeEntity>> findAll(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "10") int size,
          @RequestParam(required = true) Long categoryId) {
    Page<RecipeEntity> data = recipeService.getAll(page, size, categoryId);
    return new SystemResponse<>(200, "SUCCESS", new ResponsePage<>(data));
  }

  @GetMapping("/{id}")
  public SystemResponse<RecipeEntity> getById(@PathVariable Long id) {
    RecipeEntity dto = recipeService.getById(id);
    return new SystemResponse<>(200, "SUCCESS", dto);
  }

  @PutMapping("/{id}")
  public SystemResponse<RecipeEntity> update(@PathVariable Long id, @RequestBody RecipeDataDto recipe) {
    RecipeEntity updated = recipeService.update(id, recipe);
    return new SystemResponse<>(200, "Updated", updated);
  }

  @DeleteMapping("/{id}")
  public SystemResponse<Void> delete(@PathVariable Long id) {
    recipeService.delete(id);
    return new SystemResponse<>(200, "Deleted");
  }
}
