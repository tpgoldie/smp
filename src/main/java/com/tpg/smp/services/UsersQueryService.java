package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticatedUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersQueryService {
    Optional<AuthenticatedUser> findUserByUsernameAndPassword(String username, String secureToken);
}
