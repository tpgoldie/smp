package com.tpg.smp.auth;

import com.tpg.smp.domain.Student;
import com.tpg.smp.util.RandomStringGenerator;

import java.util.List;

import static java.util.Arrays.asList;

public class Users {
    private RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    private List<AuthenticatedUser> students = asList(createStudent("amgolding", "Ayana", "Golding", randomStringGenerator.generateRandomString()));

    private AuthenticatedUser createStudent(String username, String firstName, String lastName, String studentNumber) {
        return new Student(username, firstName, lastName, studentNumber);
    }

    public List<AuthenticatedUser> getStudents() { return students; }
}
