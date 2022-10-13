package com.icheck.backend.security;

import lombok.Data;
import org.apache.catalina.authenticator.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Data
public class AdminAccount implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Integer status;
    private int role;

    public AdminAccount() {
    }

    public AdminAccount(Long id, String username, int role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public AdminAccount(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public AdminAccount(Long id, String username, String password, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status == null || status != 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == null || status != 0;
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
