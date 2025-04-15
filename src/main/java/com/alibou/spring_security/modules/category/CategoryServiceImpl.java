package com.alibou.spring_security.modules.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository recipeRepository;

  @Override
  public Page<CategoryEntity> getAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
    return recipeRepository.findAllBy(pageable);
  }
  @Override
  public CategoryEntity getById(Long id) {
    return recipeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
  }

  @Override
  public CategoryEntity create(CategoryDataDto recipe) {
    CategoryEntity entity = new CategoryEntity();
    entity.setTitle(recipe.getTitle());
    entity.setDescription(recipe.getDescription());
    entity.setImage(recipe.getImage());
    return recipeRepository.save(entity);
  }

  @Override
  public CategoryEntity update(Long id, CategoryDataDto updated) {
    CategoryEntity existing = getById(id);
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
