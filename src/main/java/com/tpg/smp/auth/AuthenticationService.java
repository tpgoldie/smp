package com.tpg.smp.auth;

import com.google.common.base.Optional;
import com.tpg.smp.web.model.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    Optional<WebSiteUser> authenticateUser(UserModel userModel);
}
