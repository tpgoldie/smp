package com.tpg.smp.data;

import com.tpg.smp.domain.AcademicStaffMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntities;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;

import static com.tpg.smp.domain.AcademicStaffMemberType.AffiliateLecturer;

public class AcademicStaffMembersData extends UsersData {
    private final AcademicStaffMemberEntities academicStaffMemberEntities = new AcademicStaffMemberEntities();

    public AcademicStaffMembersData() {
        AcademicStaffMemberEntity personEntity = academicStaffMemberEntities.vienneWestwood();
        Name name = createDomainName(personEntity);

        AcademicStaffMember domainModel = new AcademicStaffMember(name, personEntity.getUser().getUsername(),
            personEntity.getIdentificationNumber(), AffiliateLecturer);

        add(new AcademicStaffMemberData(authenticatedUsers.vienneWestwood(), userEntities.vienneWestwood(),
                academicStaffMemberEntities.vienneWestwood(), userModels.vienneWestwood(), domainModel));
    }

    public AcademicStaffMemberData getAcademicStaffMember(int index) { return (AcademicStaffMemberData) userDataList.get(0); }
}
