package com.alibou.spring_security.modules.recipe;

import com.alibou.spring_security.base.entities.BaseEntity;
import com.alibou.spring_security.modules.category.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(columnDefinition = "TEXT")
  private String image;

  private Double grams;   // Số gram

  private Double calories; // Số calo tương ứng

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "recipe_id")
  @JsonBackReference
  private RecipeEntity recipe;
}
