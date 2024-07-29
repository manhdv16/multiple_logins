package com.dvm.employeem.service.impl;

import com.dvm.employeem.dto.request.EmployeeDto;
import com.dvm.employeem.dto.request.LoginRequest;
import com.dvm.employeem.entity.Department;
import com.dvm.employeem.entity.ERole;
import com.dvm.employeem.entity.Employee;
import com.dvm.employeem.entity.Role;
import com.dvm.employeem.repository.EmployeeRepository;
import com.dvm.employeem.service.DepartmentService;
import com.dvm.employeem.service.EmployeeService;
import com.dvm.employeem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final DepartmentService departmentService;

    // Sử dụng ConcurrentHashMap để lưu trữ khóa TOTP
    private final ConcurrentHashMap<String, String> employeeOtpKeys = new ConcurrentHashMap<>();

    @Override
    public Employee register(EmployeeDto dto) {
        Set<Role> roles = new HashSet<>();
        Role role;
        if(dto.getRole() == null) {
            role = roleService.getRoleByName(ERole.INTERNSHIP);
            roles.add(role);
        }else{
            role = roleService.getRoleByName(dto.getRole());
            roles.add(role);
        }
        Department department = departmentService.findById(dto.getDepartmentId());

        Employee employee = Employee.builder()
                .userName(dto.getUserName())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .department(department)
                .listRole(roles)
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public boolean login(LoginRequest request) {
        Employee employee = employeeRepository.findByName(request.getUserName());
        if (employee != null) {
            return employee.getPassword().equals(request.getPassword());
        }
        return false;
    }
    // Lưu khóa TOTP vào ConcurrentHashMap
    @Override
    public void saveSecretKeyForEmployee(String username, String secretKey) {
        employeeOtpKeys.put(username, secretKey);
    }

    // Lấy khóa TOTP từ ConcurrentHashMap
    @Override
    public String getSecretKeyForEmployee(String username) {
        return employeeOtpKeys.get(username);
    }
}
