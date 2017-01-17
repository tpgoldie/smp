package com.tpg.smp.persistence.entities;

public class TeachingStaffMemberEntities extends SmpEntities<TeachingStaffMemberEntity> {
    public TeachingStaffMemberEntities() {
        entities.add(createEntity("Vienne", "Westwood", TeachingStaffMemberType.AffiliateLecturer));
    }

    private TeachingStaffMemberEntity createEntity(String firstName, String lastName, TeachingStaffMemberType teachingStaffMemberType) {
        TeachingStaffMemberEntity entity = new TeachingStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setTeachingStaffMemberType(teachingStaffMemberType);

        return entity;
    }

    public TeachingStaffMemberEntity vienneWestwood() { return getEntity(0); }
}
