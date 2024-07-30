package com.dvm.employeem.controller;

import com.dvm.employeem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ldap")
public class EmbeddedLDAPController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<?> index(Authentication authentication) {
        String username = authentication.getName();
        List<String> results = employeeService.search(username);

        if (results.isEmpty()) {
            return ResponseEntity.ok("Welcome to the home page, " + username + "!");
        } else {
            return ResponseEntity.ok("Welcome to the home page, " + results.get(0) + "!");
        }
    }
}
