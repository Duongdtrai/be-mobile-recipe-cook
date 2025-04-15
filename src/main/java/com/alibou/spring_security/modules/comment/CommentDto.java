package com.alibou.spring_security.modules.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentDto {
  private String authorName;
  private String avatarUrl;
  private String content;
  private int rating;
}
