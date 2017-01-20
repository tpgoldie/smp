package com.tpg.smp.persistence.entities;

import static com.tpg.smp.persistence.entities.AcademicStaffMemberType.AffiliateLecturer;

public class AcademicStaffMemberEntities extends PersonEntities<AcademicStaffMemberEntity> {
    public AcademicStaffMemberEntities() {
        entities.add(createEntity("Vienne", "Westwood", "viwestwood", AffiliateLecturer));
    }

    private AcademicStaffMemberEntity createEntity(String firstName, String lastName, String staffMemberNumber,
                                                   AcademicStaffMemberType academicStaffMemberType) {
        AcademicStaffMemberEntity entity = new AcademicStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setStaffMemberNumber(staffMemberNumber);
        entity.setAcademicStaffMemberType(academicStaffMemberType);

        return entity;
    }

    public AcademicStaffMemberEntity vienneWestwood() { return findByUserId("viwestwood"); }
}
