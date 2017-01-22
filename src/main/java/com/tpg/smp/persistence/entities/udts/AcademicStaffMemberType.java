package com.tpg.smp.persistence.entities.udts;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class AcademicStaffMemberType extends WithDescription {
    public static final AcademicStaffMemberType PostGraduate = new AcademicStaffMemberType("Post Graduate");
    public static final AcademicStaffMemberType Professor = new AcademicStaffMemberType("Professor");
    public static final AcademicStaffMemberType AssociateProfessor = new AcademicStaffMemberType("Associate Professor");
    public static final AcademicStaffMemberType Lecturer = new AcademicStaffMemberType("Lecturer");
    public static final AcademicStaffMemberType SeniorLecturer = new AcademicStaffMemberType("Senior Lecturer");
    public static final AcademicStaffMemberType AffiliateLecturer = new AcademicStaffMemberType("Affiliate Lecturer");
    public static final AcademicStaffMemberType ResearchFellow = new AcademicStaffMemberType("Research Fellow");
    public static final AcademicStaffMemberType TeachingFellow = new AcademicStaffMemberType("Teaching Fellow");

    private static Set<AcademicStaffMemberType> TypedValues = new HashSet<>();

    public static Set<AcademicStaffMemberType> TypedValues() {
        if (!TypedValues.isEmpty()) { return TypedValues; }

        TypedValues.addAll(asList(PostGraduate, Lecturer, SeniorLecturer, AffiliateLecturer, AssociateProfessor, Professor, ResearchFellow, TeachingFellow));

        return TypedValues;
    }

    AcademicStaffMemberType(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        return (obj instanceof AcademicStaffMemberType);
    }
}
