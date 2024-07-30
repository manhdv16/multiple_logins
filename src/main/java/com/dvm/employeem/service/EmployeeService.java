package com.dvm.employeem.service;

import com.dvm.employeem.dto.request.EmployeeDto;
import com.dvm.employeem.dto.request.LoginRequest;
import com.dvm.employeem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee register(EmployeeDto employeeDto);
    boolean login(LoginRequest request);
    void saveSecretKeyForEmployee(String username, String secretKey);
    String getSecretKeyForEmployee(String username);
    List<String> search(String username);
}
