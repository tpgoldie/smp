package com.tpg.smp.auth;

public interface UserAccountStatus {
    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
