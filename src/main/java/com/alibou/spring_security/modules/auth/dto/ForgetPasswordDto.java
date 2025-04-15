package com.alibou.spring_security.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswordDto {
    @Email(message = "Email must be valid")
    @NotBlank
    @Size(max = 50)
    @Schema(description = "This is email", example = "ptd@gmail.com")
    private String email;
//    private String newPassword;
}
