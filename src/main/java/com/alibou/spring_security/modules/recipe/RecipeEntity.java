package com.alibou.spring_security.modules.recipe;

import com.alibou.spring_security.base.entities.BaseEntity;
import com.alibou.spring_security.modules.category.CategoryEntity;
import com.alibou.spring_security.modules.comment.CommentEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String image;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<IngredientEntity> ingredients = new ArrayList<>();
}
