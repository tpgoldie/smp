package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.persistence.repositories.AdministrativeStaffMembersQueryRepository;
import com.tpg.smp.persistence.repositories.StudentsQueryRepository;
import com.tpg.smp.persistence.repositories.AcademicStaffMembersQueryRepository;
import com.tpg.smp.persistence.repositories.UsersQueryRepository;
import com.tpg.smp.services.conversion.AdministrativeStaffMemberConverter;
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

    @Autowired
    public UsersQueryHandler(UsersQueryRepository userQueryRepository,
                             StudentsQueryRepository studentsQueryRepository,
                             AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository,
                             AdministrativeStaffMembersQueryRepository administrativeStaffMembersQueryRepository) {
        this.userQueryRepository = userQueryRepository;
        this.studentsQueryRepository = studentsQueryRepository;
        this.academicStaffMembersQueryRepository = academicStaffMembersQueryRepository;
        this.administrativeStaffMembersQueryRepository = administrativeStaffMembersQueryRepository;
    }

    @Override
    public Optional<UserDetails> findUserByUsernameAndPassword(String username, String secureToken) {
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

        return absent();
    }

    private Optional<UserDetails> convertToUserDetails(UserEntity userEntity, Optional<StudentEntity> studentEntity) {
        return studentEntity.transform(entity -> toStudent(userEntity, entity));
    }

    private Student toStudent(UserEntity userEntity, StudentEntity studentEntity) {
        return new Student(userEntity.getUsername(), studentEntity.getName().getFirstName(),
            studentEntity.getName().getLastName(), studentEntity.getStudentNumber());
    }
}
