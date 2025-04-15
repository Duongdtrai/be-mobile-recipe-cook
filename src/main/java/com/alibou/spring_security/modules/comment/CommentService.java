package com.alibou.spring_security.modules.comment;

import java.util.List;

public interface CommentService {
  List<CommentEntity> getByRecipe(Long recipeId);
  CommentEntity create(Long recipeId, CommentDto comment);
  void delete(Long id);
}