package com.dvm.employeem.security;

import com.dvm.employeem.entity.Employee;
import com.dvm.employeem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByName(username);
        if(employee == null) {
            throw new UsernameNotFoundException("username not found with "+ username);
        }
        return CustomUserDetail.mapUserToUserDetail(employee);
    }
}
