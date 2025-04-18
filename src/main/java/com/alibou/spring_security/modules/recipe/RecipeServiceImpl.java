package com.alibou.spring_security.modules.recipe;

import com.alibou.spring_security.modules.category.CategoryEntity;
import com.alibou.spring_security.modules.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
  private final RecipeRepository recipeRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public Page<RecipeEntity> getAll(int page, int size, Long category_id) {
    Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
    return recipeRepository.findAllBy( pageable);
  }
  @Override
  public RecipeEntity getById(Long id) {
    return recipeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
  }

  @Override
  public RecipeEntity create(RecipeDataDto recipe) {
    RecipeEntity entity = new RecipeEntity();
    List<IngredientEntity> ingredients = new ArrayList<>();
    for (IngredientDataDto ingredient : recipe.getIngredients()) {
      IngredientEntity ingredientEntity = new IngredientEntity();
      ingredientEntity.setName(ingredient.getName());
      ingredientEntity.setDescription(ingredient.getDescription());
      ingredientEntity.setImage(ingredient.getImage());
      ingredientEntity.setGrams(ingredient.getGrams());
      ingredientEntity.setCalories(ingredient.getCalories());
      ingredientEntity.setRecipe(entity);
      ingredients.add(ingredientEntity);
    }
    CategoryEntity category = categoryRepository.findById(Long.valueOf(recipe.getCategory_id()))
            .orElseThrow(() -> new RuntimeException("Category not found"));
    entity.setName(recipe.getName());
    entity.setTitle(recipe.getTitle());
    entity.setDescription(recipe.getDescription());
    entity.setImage(recipe.getImage());
    entity.setCategory(category);
    entity.setIngredients(ingredients);
    return recipeRepository.save(entity);
  }

  @Override
  public RecipeEntity update(Long id, RecipeDataDto updated) {
    RecipeEntity existing = getById(id);
    existing.setTitle(updated.getTitle());
    existing.setDescription(updated.getDescription());
    existing.setImage(updated.getImage());
    return recipeRepository.save(existing);
  }

  @Override
  public void delete(Long id) {
    recipeRepository.deleteById(id);
  }
}
