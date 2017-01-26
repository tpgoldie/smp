package com.tpg.smp.data;

import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.persistence.entities.AlumniMemberEntities;
import com.tpg.smp.persistence.entities.PersonEntity;
import com.tpg.smp.persistence.entities.UserEntity;

public class AlumniMembersData extends UsersData {
    private final AlumniMemberEntities alumniMemberEntities = new AlumniMemberEntities();

    public AlumniMembersData() {
        UserEntity userEntity = userEntities.tonyGolding();

        PersonEntity personEntity = alumniMemberEntities.tonyGolding();

        personEntity.setUser(userEntity);

        Name name = createDomainName(personEntity);

        AlumniMember domainModel = new AlumniMember(name, alumniMemberEntities.tonyGolding().getUser().getUsername(), alumniMemberEntities.tonyGolding().getIdentificationNumber());

        add(new AlumniMemberData(authenticatedUsers.tonyGolding(), userEntities.tonyGolding(),
                alumniMemberEntities.tonyGolding(), userModels.tonyGolding(), domainModel));
    }

    public AlumniMemberData getAlumniMember(int index) { return (AlumniMemberData) userDataList.get(0); }
}
