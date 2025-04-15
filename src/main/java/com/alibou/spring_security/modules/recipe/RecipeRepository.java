package com.alibou.spring_security.modules.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
  Page<RecipeEntity> findAllBy(Pageable pageable);

  Page<RecipeEntity> findAllByCategoryId(Long categoryId, Pageable pageable);

}
