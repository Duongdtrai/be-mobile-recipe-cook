package com.alibou.spring_security.modules.auth.dto;

import com.alibou.spring_security.modules.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
    private String accessToken;
    private String refreshToken;
    private User user;
}
