package com.dvm.employeem.service.impl;

import com.dvm.employeem.entity.ERole;
import com.dvm.employeem.entity.Role;
import com.dvm.employeem.repository.RoleRepository;
import com.dvm.employeem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role getRoleByName(ERole role) {
        return roleRepository.findByName(role);
    }
}
