package com.dvm.employeem.service;

import com.dvm.employeem.entity.ERole;
import com.dvm.employeem.entity.Role;

public interface RoleService {
    Role getRoleByName(ERole role);
}
