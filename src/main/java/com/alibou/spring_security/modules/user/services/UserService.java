package com.alibou.spring_security.modules.user.services;


import com.alibou.spring_security.modules.user.repositories.TokenRepository;
import com.alibou.spring_security.utils.IMAGE;
import com.alibou.spring_security.base.middleware.responses.Response;
import com.alibou.spring_security.base.middleware.responses.ResponsePage;
import com.alibou.spring_security.base.middleware.responses.SystemResponse;
import com.alibou.spring_security.modules.user.dto.ChangePasswordDto;
import com.alibou.spring_security.modules.user.dto.UpdateDto;
import com.alibou.spring_security.modules.user.entities.User;
import com.alibou.spring_security.modules.user.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<SystemResponse<ResponsePage<User>>> list(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<User> userPage = repository.findAll(paging);
            return Response.ok(new ResponsePage<User>(userPage));
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }

    public ResponseEntity<SystemResponse<User>> retrieveBy() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            return Response.ok(user);
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }

    public ResponseEntity<SystemResponse<User>> update(UpdateDto data) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            user.setAddress(data.getAddress());
            user.setFullname(data.getFullname());
            user.setPhoneNumber(data.getPhoneNumber());
            repository.save(user);
            return Response.ok(user);
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }

    public ResponseEntity<SystemResponse<Boolean>> delete() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            user.setStatus(false);
            repository.save(user);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }

    public ResponseEntity<SystemResponse<String>> uploadImage(MultipartFile file) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            String key = IMAGE.USER.toString().toLowerCase() + "/" + file.getOriginalFilename();
            String url = null;
            user.setAvatar(url);
            repository.save(user);
            return Response.ok(url);
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }

    public ResponseEntity<SystemResponse<Boolean>> changePassword(ChangePasswordDto data) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            user.setPassword(passwordEncoder.encode(data.getPassword()));
            repository.save(user);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }

    public ResponseEntity<SystemResponse<Boolean>> logout(HttpServletRequest request, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            String token = request.getHeader("Authorization").substring(7);
            tokenRepository.deleteByUserIdAndToken(user.getId(), token);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.badRequest(500, e.getMessage());
        }
    }
}
