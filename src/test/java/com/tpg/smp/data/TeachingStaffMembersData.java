package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.TeachingStaffMemberEntities;

public class TeachingStaffMembersData extends UsersData {
    private final TeachingStaffMemberEntities teachingStaffMemberEntities = new TeachingStaffMemberEntities();

    public TeachingStaffMembersData() {
        userDataList.add(new TeachingStaffMemberData(userEntities.vienneWestwood(),
                teachingStaffMemberEntities.vienneWestwood(), userModels.vienneWestwood()));
    }

    public TeachingStaffMemberData getTestTeachingStaffMember(int index) { return (TeachingStaffMemberData) userDataList.get(0); }
}
