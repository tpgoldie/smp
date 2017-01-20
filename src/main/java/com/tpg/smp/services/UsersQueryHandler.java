package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.*;
import com.tpg.smp.persistence.repositories.*;
import com.tpg.smp.services.conversion.AdministrativeStaffMemberConverter;
import com.tpg.smp.services.conversion.AlumniMemberConverter;
import com.tpg.smp.services.conversion.StudentConverter;
import com.tpg.smp.services.conversion.AcademicStaffMemberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.google.common.base.Optional.absent;

@Service
public class UsersQueryHandler implements UsersQueryService {
    private UsersQueryRepository userQueryRepository;

    private StudentsQueryRepository studentsQueryRepository;

    private AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository;

    private AdministrativeStaffMembersQueryRepository administrativeStaffMembersQueryRepository;

    private AlumniMembersQueryRepository alumniMembersQueryRepository;

    @Autowired
    public UsersQueryHandler(UsersQueryRepository userQueryRepository,
                             StudentsQueryRepository studentsQueryRepository,
                             AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository,
                             AdministrativeStaffMembersQueryRepository administrativeStaffMembersQueryRepository,
                             AlumniMembersQueryRepository alumniMembersQueryRepository) {
        this.userQueryRepository = userQueryRepository;
        this.studentsQueryRepository = studentsQueryRepository;
        this.academicStaffMembersQueryRepository = academicStaffMembersQueryRepository;
        this.administrativeStaffMembersQueryRepository = administrativeStaffMembersQueryRepository;
        this.alumniMembersQueryRepository = alumniMembersQueryRepository;
    }

    @Override
    public Optional<AuthenticatedUser> findUserByUsernameAndPassword(String username, String secureToken) {
        Optional<UserEntity> userEntity = userQueryRepository.findByUsernameAndSecureToken(username, secureToken);

        if (!userEntity.isPresent()) { return absent(); }

        Optional<StudentEntity> studentEntity = studentsQueryRepository.findById(userEntity.get().getPersonId());

        if (studentEntity.isPresent()) {
            return userEntity.transform(e -> new StudentConverter(e).convert(studentEntity.get()));
        }

        Optional<AcademicStaffMemberEntity> academicStaffMember = academicStaffMembersQueryRepository.findById(userEntity.get().getPersonId());

        if (academicStaffMember.isPresent()) {
            return userEntity.transform(e -> new AcademicStaffMemberConverter(e).convert(academicStaffMember.get()));
        }

        Optional<AdministrativeStaffMemberEntity> adminStaffMember = administrativeStaffMembersQueryRepository.findById(userEntity.get().getPersonId());

        if (adminStaffMember.isPresent()) {
            return userEntity.transform(e -> new AdministrativeStaffMemberConverter(e).convert(adminStaffMember.get()));
        }

        Optional<AlumniMemberEntity> alumniMember = alumniMembersQueryRepository.findById(userEntity.get().getPersonId());

        if (alumniMember.isPresent()) {
            return userEntity.transform(e -> new AlumniMemberConverter(e).convert(alumniMember.get()));
        }

        return absent();
    }
}
