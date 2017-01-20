package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntities;

public class AdministrativeStaffMembersData extends UsersData {
    private final AdministrativeStaffMemberEntities administrativeStaffMemberEntities = new AdministrativeStaffMemberEntities();

    public AdministrativeStaffMembersData() {
        add(new AdministrativeStaffMemberData(authenticatedUsers.rogerJohnson(), userEntities.rogerJohnson(),
            administrativeStaffMemberEntities.rogerJohnson(), userModels.rogerJohnson()));
    }

    public AdministrativeStaffMemberData getAdministrativeStaffMember(int index) { return (AdministrativeStaffMemberData) userDataList.get(index); }
}
