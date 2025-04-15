package com.alibou.spring_security.modules.comment;

import com.alibou.spring_security.base.entities.BaseEntity;
import com.alibou.spring_security.modules.recipe.RecipeEntity;
import com.alibou.spring_security.modules.user.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private int rating;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "recipe_id")
  @JsonIgnore // Chặn khi trả JSON
  private RecipeEntity recipe;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
}
