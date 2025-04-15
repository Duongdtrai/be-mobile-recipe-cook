package com.alibou.spring_security.modules.auth.controllers;

import com.alibou.spring_security.base.middleware.responses.SystemResponse;
import com.alibou.spring_security.modules.auth.dto.*;
import com.alibou.spring_security.modules.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth", description = "Authentication APIs")
//@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private final AuthenticationService service;


    @PostMapping("/sign-up")
    public ResponseEntity<SystemResponse<SignUpResponseDto>> register(
            @Valid @RequestBody RegisterRequestDto request
    ) {
        return service.register(request);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SystemResponse<AuthenticationResponseDto>> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return service.login(request);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<SystemResponse<Boolean>> forgotPassword(
            @Valid @RequestBody ForgetPasswordDto request
    ) {
        return service.forgotPassword(request);
    }


    @PostMapping("/otp")
    public ResponseEntity<SystemResponse<Boolean>> otp(
            @Valid @RequestBody OtpDto request
    ) {
        return service.otp(request);
    }
}
