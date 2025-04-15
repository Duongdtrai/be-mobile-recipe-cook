package com.alibou.spring_security.modules.category;

import org.springframework.data.domain.Page;

public interface CategoryService {
  Page<CategoryEntity> getAll(int page, int size);
  CategoryEntity getById(Long id);
  CategoryEntity create(CategoryDataDto recipe);
  CategoryEntity update(Long id, CategoryDataDto recipe);
  void delete(Long id);
}
