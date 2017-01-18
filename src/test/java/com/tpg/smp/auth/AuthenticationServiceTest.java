package com.tpg.smp.auth;

import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.services.UsersQueryService;
import com.tpg.smp.web.model.UserModel;
import org.junit.Before;
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

    private StudentData studentData = new StudentsData().getStudent(0);

    @Test
    public void handleValidUserLoginRequest_validUser_userIsAuthenticated() {
        UserModel userModel = studentData.getUserModel();

        AuthenticatedUser authenticatedUser = studentData.getAuthenticatedUser();

        when(usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken())).thenReturn(of(authenticatedUser));

        WebSiteUser actual = (WebSiteUser) authenticationService.authenticateUser(userModel).get();

        assertThat(actual, is(authenticatedUser));

        verify(usersQueryService).findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken());
    }
}
