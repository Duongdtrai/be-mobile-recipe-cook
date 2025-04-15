package com.alibou.spring_security.modules.category;

import com.alibou.spring_security.base.entities.BaseEntity;
import com.alibou.spring_security.modules.comment.CommentEntity;
import com.alibou.spring_security.modules.recipe.RecipeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String image;
}
