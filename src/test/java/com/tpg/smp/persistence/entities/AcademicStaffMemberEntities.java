package com.tpg.smp.persistence.entities;

import static com.tpg.smp.persistence.entities.AcademicStaffMemberType.AffiliateLecturer;

public class AcademicStaffMemberEntities extends SmpEntities<AcademicStaffMemberEntity> {
    public AcademicStaffMemberEntities() {
        entities.add(createEntity("Vienne", "Westwood", AffiliateLecturer));
    }

    private AcademicStaffMemberEntity createEntity(String firstName, String lastName, AcademicStaffMemberType academicStaffMemberType) {
        AcademicStaffMemberEntity entity = new AcademicStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setAcademicStaffMemberType(academicStaffMemberType);

        return entity;
    }

    public AcademicStaffMemberEntity vienneWestwood() { return getEntity(0); }
}
