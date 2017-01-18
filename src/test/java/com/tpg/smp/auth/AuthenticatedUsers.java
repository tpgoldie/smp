package com.tpg.smp.auth;

import com.google.common.base.Optional;
import com.tpg.smp.data.PasswordGenerator;
import com.tpg.smp.domain.AcademicStaffMember;
import com.tpg.smp.domain.AdministrativeStaffMember;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.AcademicStaffMemberType;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberType;
import com.tpg.smp.util.RandomStringGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.tpg.smp.persistence.entities.AcademicStaffMemberType.AffiliateLecturer;
import static com.tpg.smp.persistence.entities.AdministrativeStaffMemberType.Registrar;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class AuthenticatedUsers {
    private RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    private List<AuthenticatedUser> authenticatedUsers;

    public AuthenticatedUsers() {
        authenticatedUsers = asList(
                createAdministrativeStaffMember("rojohnson", "Roger", "Johnson", Registrar),
                createAcademicStaffMember("viwestwood", "Vienne", "Westwood", AffiliateLecturer),
                createStudent("amgolding", "Ayana", "Golding", randomStringGenerator.generateRandomString())
        );
    }

    private AuthenticatedUser createAcademicStaffMember(String username, String firstName, String lastName,
                                                        AcademicStaffMemberType academicStaffMemberType) {
        return new AcademicStaffMember(username, firstName, lastName, academicStaffMemberType);
    }

    private AuthenticatedUser createAdministrativeStaffMember(String username, String firstName, String lastName,
                                                              AdministrativeStaffMemberType administrativeStaffMemberType) {
        return new AdministrativeStaffMember(username, firstName, lastName, administrativeStaffMemberType);
    }

    private AuthenticatedUser createStudent(String username, String firstName, String lastName, String studentNumber) {
        return new Student(username, firstName, lastName, studentNumber);
    }

    public Optional<AuthenticatedUser> findAuthenticatedUserById(String id) {
        List<AuthenticatedUser> found = authenticatedUsers
                .stream()
                .filter(au -> au.getUsername().equalsIgnoreCase(id)).collect(toList());

        return (found.size() > 0) ? of(found.get(0)) : absent();
    }

    public AuthenticatedUser rogerJohnson() { return authenticatedUsers.get(0); }

    public AuthenticatedUser vienneWestwood() { return authenticatedUsers.get(1); }

    public AuthenticatedUser ayanaGolding() { return authenticatedUsers.get(2); }
}
