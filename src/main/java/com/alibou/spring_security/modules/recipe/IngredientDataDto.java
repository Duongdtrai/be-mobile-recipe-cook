package com.alibou.spring_security.modules.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDataDto {
  private String name;
  private String description;
  private String image;
  private Double grams;   // Số gram
  private Double calories; // Số calo tương ứng
}
