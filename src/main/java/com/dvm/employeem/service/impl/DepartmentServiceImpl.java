package com.dvm.employeem.service.impl;

import com.dvm.employeem.entity.Department;
import com.dvm.employeem.repository.DepartmentRepository;
import com.dvm.employeem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public Department findById(String id) {
        return departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Department not found"));
    }
}
