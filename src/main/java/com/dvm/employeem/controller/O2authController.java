package com.dvm.employeem.controller;

import com.dvm.employeem.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class O2authController {
    // login with o2auth
    @GetMapping("/login-with-o2auth")
    public ResponseEntity<?> loginWithO2auth(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No OAuth2User principal");
        }
        ApiResponse<Map<String, Object>> response = new ApiResponse(200, "Welcome to home page", principal.getAttributes());
        return ResponseEntity.ok(response);
    }

}
