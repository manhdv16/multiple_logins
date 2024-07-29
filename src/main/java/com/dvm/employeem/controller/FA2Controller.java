package com.dvm.employeem.controller;

import com.dvm.employeem.dto.request.EmployeeDto;
import com.dvm.employeem.dto.request.LoginRequest;
import com.dvm.employeem.dto.response.ApiResponse;
import com.dvm.employeem.entity.Employee;
import com.dvm.employeem.service.EmployeeService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class FA2Controller {

    private final EmployeeService employeeService;
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    // login with 2 factor authentication
    @PostMapping("/login")
    public ResponseEntity<?> loginWith2FA(@RequestBody LoginRequest request) {
        boolean isLoginSuccess = employeeService.login(request);
        if (!isLoginSuccess) {
            ApiResponse<String> response = new ApiResponse<>(400, "username or password is incorrect", null);
            return ResponseEntity.ok(response);
        }
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secretKey = key.getKey();
        employeeService.saveSecretKeyForEmployee(request.getUserName(), secretKey);
        ApiResponse<String> response = new ApiResponse<>(200, "username and password is correct. Please auth step 2 by otp", secretKey);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verify2fa(@RequestParam String userName, @RequestParam int code) {
        String secretKey = employeeService.getSecretKeyForEmployee(userName);

        if (secretKey == null) {
            ApiResponse<String> response = new ApiResponse<>(400, "Employee not found or secret key not generated", null);
            return ResponseEntity.badRequest().body(response);
        }
        // code nay se lay tu googleauthenticator app
        boolean isCodeValid = gAuth.authorize(secretKey, code);
        if (isCodeValid) {
            ApiResponse<String> response = new ApiResponse<>(200, "Welcome to the home page", "Code is valid");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<String> response = new ApiResponse<>(400, "Invalid code, please try again", "Invalid code");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody EmployeeDto employeeDto) {
        Employee e = employeeService.register(employeeDto);
        ApiResponse<Employee> response = new ApiResponse<>(200, "register successfully", e);
        return ResponseEntity.ok(response);
    }
}
