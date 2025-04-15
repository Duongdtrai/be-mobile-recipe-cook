package com.alibou.spring_security.modules.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  List<CommentEntity> findByRecipeIdOrderByCreatedAtDesc(Long recipeId);

}
