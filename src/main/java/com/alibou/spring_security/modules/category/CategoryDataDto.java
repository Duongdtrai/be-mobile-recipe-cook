package com.alibou.spring_security.modules.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDataDto {
  private String title;
  private String description;
  private String image;
}
