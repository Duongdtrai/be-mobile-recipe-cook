package com.alibou.spring_security.modules.comment;

import com.alibou.spring_security.modules.recipe.RecipeEntity;
import com.alibou.spring_security.modules.recipe.RecipeRepository;
import com.alibou.spring_security.modules.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final RecipeRepository recipeRepository;

  @Override
  public List<CommentEntity> getByRecipe(Long recipeId) {
    return commentRepository.findByRecipeIdOrderByCreatedAtDesc(recipeId);
  }


  @Override
  public CommentEntity create(Long recipeId, CommentDto comment) {
    // Tìm RecipeEntity theo recipeId
    RecipeEntity recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User userDetails = (User) authentication.getPrincipal();
    System.out.println("User details: " + userDetails.toString());
    // Tạo CommentEntity và ánh xạ các trường từ CommentDto
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setContent(comment.getContent());
    commentEntity.setRating(comment.getRating());
    commentEntity.setRecipe(recipe);
    commentEntity.setUser(userDetails);

    // Lưu CommentEntity vào repository
    return commentRepository.save(commentEntity);
  }

  @Override
  public void delete(Long id) {
    commentRepository.deleteById(id);
  }
}
