package com.alibou.spring_security.modules.user.controllers;

import com.alibou.spring_security.base.middleware.responses.ResponsePage;
import com.alibou.spring_security.base.middleware.responses.SystemResponse;
import com.alibou.spring_security.modules.user.dto.ChangePasswordDto;
import com.alibou.spring_security.modules.user.dto.UpdateDto;
import com.alibou.spring_security.modules.user.entities.User;
import com.alibou.spring_security.modules.user.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@SecurityRequirement(name = "Authorization")
@Tag(name = "Users", description = "Users APIs")
public class UserController {
    @Autowired
    private final UserService service;

    @GetMapping("/list")
    public ResponseEntity<SystemResponse<ResponsePage<User>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.list(page, size);
    }

    @GetMapping("/detail")
    public ResponseEntity<SystemResponse<User>> retrieveBy() {
        return service.retrieveBy();
    }

    @PatchMapping("/change-info")
    public ResponseEntity<SystemResponse<User>> update(@RequestBody UpdateDto data) {
        return service.update(data);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<SystemResponse<Boolean>> delete() {
        return service.delete();
    }

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SystemResponse<String>> uploadImage(@RequestPart("file") MultipartFile file) {
        return service.uploadImage(file);
    }

    @PostMapping("/change-password")
    public ResponseEntity<SystemResponse<Boolean>> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return service.changePassword(changePasswordDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<SystemResponse<Boolean>> logout(HttpServletRequest request, Authentication authentication) {
        return service.logout(request, authentication);
    }
}
