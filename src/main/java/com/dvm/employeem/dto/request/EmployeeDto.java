package com.dvm.employeem.dto.request;

import com.dvm.employeem.entity.ERole;
import lombok.Getter;

@Getter
public class EmployeeDto {
    private String userName;
    private String fullName;
    private String password;
    private String departmentId;
    private ERole role;
}
