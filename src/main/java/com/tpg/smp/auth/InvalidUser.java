package com.tpg.smp.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.emptyList;

public class InvalidUser extends WebSiteUser {
    private final String message;

    public InvalidUser(String username, String message) {
        this.message = String.format("%s : %s", username, message);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return emptyList();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
