package com.tpg.smp.auth;

import com.tpg.smp.domain.Student;

import java.util.List;

import static java.util.Arrays.asList;

public class Users {
    private List<AuthenticatedUser> students = asList(createStudent("amgolding", "Ayana", "Golding"));

    private AuthenticatedUser createStudent(String username, String firstName, String lastName) {
        return new Student(username, firstName, lastName);
    }

    public List<AuthenticatedUser> getStudents() { return students; }
}
