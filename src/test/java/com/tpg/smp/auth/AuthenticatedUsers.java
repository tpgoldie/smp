package com.tpg.smp.auth;

import com.google.common.base.Optional;
import com.tpg.smp.domain.*;
import com.tpg.smp.domain.AcademicStaffMemberType;
import com.tpg.smp.domain.AdministrativeStaffMemberType;
import com.tpg.smp.util.RandomStringGenerator;

import java.util.List;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.tpg.smp.domain.AcademicStaffMemberType.AffiliateLecturer;
import static com.tpg.smp.domain.AdministrativeStaffMemberType.Registrar;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class AuthenticatedUsers {
    private RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    private List<AuthenticatedUser> authenticatedUsers;

    public AuthenticatedUsers() {
        authenticatedUsers = asList(
                createAdministrativeStaffMember("rojohnson", "Roger", "Johnson", randomStringGenerator.generateRandomString(), Registrar),
                createAcademicStaffMember("viwestwood", "Vienne", "Westwood", randomStringGenerator.generateRandomString(), AffiliateLecturer),
                createStudent("midanque", "Micheal", "Danque", randomStringGenerator.generateRandomString()),
                createAlumniMember("tpgolding", "Tony", "Golding", randomStringGenerator.generateRandomString())
        );
    }

    private AuthenticatedUser createAcademicStaffMember(String username, String firstName, String lastName, String staffMemberNumber,
                                                        AcademicStaffMemberType academicStaffMemberType) {
        return new AcademicStaffMember(new Name(firstName, lastName), username, staffMemberNumber, academicStaffMemberType);
    }

    private AuthenticatedUser createAdministrativeStaffMember(String username, String firstName, String lastName, String staffMemberNumber,
                                                              AdministrativeStaffMemberType administrativeStaffMemberType) {
        return new AdministrativeStaffMember(new Name(firstName, lastName), username, staffMemberNumber, administrativeStaffMemberType);
    }

    private AuthenticatedUser createStudent(String username, String firstName, String lastName, String studentNumber) {
        return new Student(new Name(firstName, lastName), username, studentNumber);
    }

    private AuthenticatedUser createAlumniMember(String username, String firstName, String lastName, String alumniMemberNumber) {
        return new AlumniMember(new Name(firstName, lastName), username, alumniMemberNumber);
    }

    public Optional<AuthenticatedUser> findAuthenticatedUserById(String id) {
        List<AuthenticatedUser> found = authenticatedUsers
                .stream()
                .filter(au -> au.getUsername().equalsIgnoreCase(id)).collect(toList());

        return (found.size() > 0) ? of(found.get(0)) : absent();
    }

    private AuthenticatedUser findById(String id) {
        java.util.Optional<AuthenticatedUser> found = authenticatedUsers.stream().filter(au -> au.getUsername().equalsIgnoreCase(id)).findAny();

        if (found.isPresent()) { return found.get(); }

        throw new RuntimeException(String.format("Authenticated user %s not found", id));
    }

    public AuthenticatedUser rogerJohnson() { return findById("rojohnson"); }

    public AuthenticatedUser vienneWestwood() { return findById("viwestwood"); }

    public AuthenticatedUser michaelDanque() { return findById("midanque"); }

    public AuthenticatedUser tonyGolding() { return findById("tpgolding"); }
}
