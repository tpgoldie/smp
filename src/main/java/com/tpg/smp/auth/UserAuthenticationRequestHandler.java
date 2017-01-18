package com.tpg.smp.auth;

import com.google.common.base.Optional;
import com.tpg.smp.services.UsersQueryService;
import com.tpg.smp.web.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationRequestHandler implements AuthenticationService {
    private UsersQueryService usersQueryService;

    @Autowired
    public UserAuthenticationRequestHandler(UsersQueryService usersQueryService) {
        this.usersQueryService = usersQueryService;
    }

    @Override
    public Optional<AuthenticatedUser> authenticateUser(UserModel userModel) {
        return usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
