package com.dvm.employeem.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String userName;
    private String password;
    private String departmentId;
}
