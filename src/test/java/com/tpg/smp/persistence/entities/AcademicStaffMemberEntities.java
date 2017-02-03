package com.tpg.smp.persistence.entities;

import com.tpg.smp.domain.AcademicStaffMemberType;
import com.tpg.smp.domain.GenderType;

import static com.tpg.smp.domain.AcademicStaffMemberType.AffiliateLecturer;

public class AcademicStaffMemberEntities extends PersonEntities<AcademicStaffMemberEntity> {
    public AcademicStaffMemberEntities() {
        entities.add(createEntity("Vienne", "Westwood", GenderType.Female, "viwestwood", AffiliateLecturer));
    }

    private AcademicStaffMemberEntity createEntity(String firstName, String lastName, GenderType gender, String staffMemberNumber,
                                                   AcademicStaffMemberType academicStaffMemberType) {
        AcademicStaffMemberEntity entity = new AcademicStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setGender(gender);
        entity.setStaffMemberNumber(staffMemberNumber);
        entity.setAcademicStaffMemberType(academicStaffMemberType);

        return entity;
    }

    public AcademicStaffMemberEntity vienneWestwood() { return findByUserId("viwestwood"); }
}
