package com.tpg.smp.services.context;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.auth.UserAuthenticationRequestHandler;
import com.tpg.smp.persistence.repositories.StudentsQueryRepository;
import com.tpg.smp.persistence.repositories.UsersQueryRepository;
import com.tpg.smp.services.UsersQueryHandler;
import com.tpg.smp.services.UsersQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.persistence.repositories"})
public class ServicesConfig {
    @Autowired
    private UsersQueryRepository usersQueryRepository;

    @Autowired
    private StudentsQueryRepository studentsQueryRepository;

    @Bean
    public UsersQueryService usersQueryService() {
        return new UsersQueryHandler(usersQueryRepository, studentsQueryRepository);
    }

    @Bean
    public AuthenticationService authenticationService() {
        return new UserAuthenticationRequestHandler(usersQueryService());
    }
}
