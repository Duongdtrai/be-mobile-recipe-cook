package com.alibou.spring_security.modules.comment;

import com.alibou.spring_security.base.middleware.responses.SystemResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
@SecurityRequirement(name = "Authorization")
@Tag(name = "Comments", description = "Comment APIs")
public class CommentController {

  private final CommentService commentService;

  @GetMapping("/by-recipe/{recipeId}")
  public SystemResponse<List<CommentEntity>> getByRecipe(@PathVariable Long recipeId) {
    return new SystemResponse<>(200, "SUCCESS", commentService.getByRecipe(recipeId));
  }

  @PostMapping("/recipe/{recipeId}")
  public SystemResponse<CommentEntity> create(@PathVariable Long recipeId, @RequestBody CommentDto comment) {
    return new SystemResponse<>(201, "CREATED", commentService.create(recipeId, comment));
  }

  @DeleteMapping("/{id}")
  public SystemResponse<Void> delete(@PathVariable Long id) {
    commentService.delete(id);
    return new SystemResponse<>(200, "DELETED");
  }
}
