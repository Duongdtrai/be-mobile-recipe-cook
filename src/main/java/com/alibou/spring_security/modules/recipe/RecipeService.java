package com.alibou.spring_security.modules.recipe;

import org.springframework.data.domain.Page;

import java.util.List;

public interface  RecipeService {
  Page<RecipeEntity> getAll(int page, int size, Long categoryId);
  RecipeEntity getById(Long id);
  RecipeEntity create(RecipeDataDto recipe);
  RecipeEntity update(Long id, RecipeDataDto recipe);
  void delete(Long id);
}
