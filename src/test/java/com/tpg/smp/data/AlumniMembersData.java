package com.tpg.smp.data;

import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.persistence.entities.AlumniMemberEntities;

public class AlumniMembersData extends UsersData {
    private final AlumniMemberEntities alumniMemberEntities = new AlumniMemberEntities();

    public AlumniMembersData() {
        Name name = createDomainName(alumniMemberEntities.tonyGolding());

        AlumniMember domainModel = new AlumniMember(name, alumniMemberEntities.tonyGolding().getUser().getUsername(), alumniMemberEntities.tonyGolding().getIdentificationNumber());

        add(new AlumniMemberData(authenticatedUsers.tonyGolding(), userEntities.tonyGolding(),
                alumniMemberEntities.tonyGolding(), userModels.tonyGolding(), domainModel));
    }

    public AlumniMemberData getAlumniMember(int index) { return (AlumniMemberData) userDataList.get(0); }
}
