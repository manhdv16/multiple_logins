package com.dvm.employeem.repository;

import com.dvm.employeem.entity.ERole;
import com.dvm.employeem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(ERole role);
}
