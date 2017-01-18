package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.AcademicStaffMemberEntities;

public class AcademicStaffMembersData extends UsersData {
    private final AcademicStaffMemberEntities academicStaffMemberEntities = new AcademicStaffMemberEntities();

    public AcademicStaffMembersData() {
        add(new AcademicStaffMemberData(authenticatedUsers.vienneWestwood(), userEntities.vienneWestwood(),
                academicStaffMemberEntities.vienneWestwood(), userModels.vienneWestwood()));
    }

    public AcademicStaffMemberData getAcademicStaffMember(int index) { return (AcademicStaffMemberData) userDataList.get(0); }
}
