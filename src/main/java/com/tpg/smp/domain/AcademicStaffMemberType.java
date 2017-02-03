package com.tpg.smp.domain;

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

    private static TypedValues<AcademicStaffMemberType> TypedValues = new TypedValues<>(asList(PostGraduate, Lecturer,
            SeniorLecturer, AffiliateLecturer, AssociateProfessor, Professor, ResearchFellow, TeachingFellow));

    public static TypedValues<AcademicStaffMemberType> TypedValues() { return TypedValues; }

    AcademicStaffMemberType(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        return (obj instanceof AcademicStaffMemberType);
    }
}
