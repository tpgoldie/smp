package com.tpg.smp.auth;

import com.tpg.smp.services.UsersQueryService;
import com.tpg.smp.web.model.UserModel;
import com.tpg.smp.web.model.UserModels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.base.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AuthenticationServiceTest {
    @Configuration
    static class Config {
        @MockBean
        private UsersQueryService usersQueryService;

        @Bean
        public UsersQueryService userQueryService() { return usersQueryService; }

        @Bean
        public AuthenticationService authenticationService() {
            return new UserAuthenticationRequestHandler(usersQueryService);
        }
    }

    @Autowired
    private UsersQueryService usersQueryService;

    @Autowired
    private AuthenticationService authenticationService;

    private UserModels userModels = new UserModels();

    private Users users = new Users();

    @Test
    public void handleValidUserLoginRequest_validUser_userIsAuthenticated() {
        UserModel userModel = userModels.getUserModel(0);

        AuthenticatedUser authenticatedUser = users.getStudents().get(0);

        when(usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken())).thenReturn(of(authenticatedUser));

        WebSiteUser actual = (WebSiteUser) authenticationService.authenticateUser(userModel).get();

        assertThat(actual, is(authenticatedUser));

        verify(usersQueryService).findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken());
    }
}
