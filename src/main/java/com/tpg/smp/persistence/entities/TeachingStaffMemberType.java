package com.tpg.smp.persistence.entities;

public enum TeachingStaffMemberType {
    PostGraduate("Post Graduate"), Lecturer("Lecturer"), SeniorLecturer("Senior Lecturer"), AffiliateLecturer("Affiliate Lecturer");

    private final String description;

    TeachingStaffMemberType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() { return getDescription(); }
}
