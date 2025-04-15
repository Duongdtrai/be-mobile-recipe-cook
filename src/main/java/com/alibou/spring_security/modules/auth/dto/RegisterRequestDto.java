package com.alibou.spring_security.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @Email(message = "Email must be valid")
    @NotBlank
    @Size(max = 50)
    @Schema(description = "This is email", example = "user@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "This is password", example = "123123")
    private String password;

    @NotBlank
    @Schema(description = "This is fullname", example = "Pham Tung Duong")
    private String fullname;

    @Schema(description = "This is address", example = "Ha Noi")
    private String address;

    @Schema(description = "This is phoneNumber", example = "0123456789")
    private String phoneNumber;
}
