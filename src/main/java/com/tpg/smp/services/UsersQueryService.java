package com.tpg.smp.services;

import com.google.common.base.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersQueryService {
    Optional<UserDetails> findUserByUsernameAndPassword(String username, String secureToken);
}
