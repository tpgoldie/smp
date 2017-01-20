package com.tpg.smp.services.context;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.auth.UserAuthenticationRequestHandler;
import com.tpg.smp.persistence.context.PersistenceConfig;
import com.tpg.smp.persistence.repositories.*;
import com.tpg.smp.services.UsersQueryHandler;
import com.tpg.smp.services.UsersQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class ServicesConfig {
    @Autowired
    private UsersQueryRepository usersQueryRepository;

    @Autowired
    private StudentsQueryRepository studentsQueryRepository;

    @Autowired
    private AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository;

    @Autowired
    private AdministrativeStaffMembersQueryRepository administrativeStaffMembersQueryRepository;

    @Autowired
    private AlumniMembersQueryRepository alumniMembersQueryRepository;

    @Bean
    public UsersQueryService usersQueryService() {
        return new UsersQueryHandler(usersQueryRepository, studentsQueryRepository,
            academicStaffMembersQueryRepository, administrativeStaffMembersQueryRepository,
            alumniMembersQueryRepository);
    }

    @Bean
    public AuthenticationService authenticationService() {
        return new UserAuthenticationRequestHandler(usersQueryService());
    }
}
