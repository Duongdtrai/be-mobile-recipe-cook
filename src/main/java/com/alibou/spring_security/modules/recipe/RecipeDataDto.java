package com.alibou.spring_security.modules.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDataDto {
  private String name;
  private String title;
  private String description;
  private String image;
  private Long category_id;
}
