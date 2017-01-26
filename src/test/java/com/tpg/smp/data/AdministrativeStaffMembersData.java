package com.tpg.smp.data;

import com.tpg.smp.domain.AdministrativeStaffMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.domain.Person;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntities;
import com.tpg.smp.persistence.entities.PersonEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static com.tpg.smp.domain.AdministrativeStaffMemberType.Registrar;

public class AdministrativeStaffMembersData extends UsersData {
    private final AdministrativeStaffMemberEntities administrativeStaffMemberEntities = new AdministrativeStaffMemberEntities();

    public AdministrativeStaffMembersData() {
        UserEntity userEntity = userEntities.rogerJohnson();
        PersonEntity personEntity = administrativeStaffMemberEntities.rogerJohnson();
        personEntity.setUser(userEntity);

        Name name = createDomainName(personEntity);

        Person domainModel = new AdministrativeStaffMember(name, personEntity.getUser().getUsername(),
                personEntity.getIdentificationNumber(), Registrar);

        add(new AdministrativeStaffMemberData(authenticatedUsers.rogerJohnson(), userEntities.rogerJohnson(),
            administrativeStaffMemberEntities.rogerJohnson(), userModels.rogerJohnson(), domainModel));
    }

    public AdministrativeStaffMemberData getAdministrativeStaffMember(int index) { return (AdministrativeStaffMemberData) userDataList.get(index); }
}
