package com.dvm.employeem.security;

import com.dvm.employeem.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
public class CustomUserDetail implements UserDetails {

    private int id;
    private String userName;
    @JsonIgnore
    private String password;

    public static CustomUserDetail mapUserToUserDetail(Employee employee) {
        return CustomUserDetail.builder()
                .userName(employee.getUserName())
                .password(employee.getPassword())
                .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
