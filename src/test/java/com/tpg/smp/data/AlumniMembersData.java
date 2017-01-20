package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.AlumniMemberEntities;

public class AlumniMembersData extends UsersData {
    private final AlumniMemberEntities alumniMemberEntities = new AlumniMemberEntities();

    public AlumniMembersData() {
        add(new AlumniMemberData(authenticatedUsers.tonyGolding(), userEntities.tonyGolding(),
                alumniMemberEntities.tonyGolding(), userModels.tonyGolding()));
    }

    public AlumniMemberData getAlumniMember(int index) { return (AlumniMemberData) userDataList.get(0); }
}
