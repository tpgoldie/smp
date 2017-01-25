package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUsers;
import com.tpg.smp.domain.Name;
import com.tpg.smp.persistence.entities.PersonEntity;
import com.tpg.smp.persistence.entities.UserEntities;
import com.tpg.smp.web.model.UserModels;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class UsersData {
    private static final PasswordGenerator PASSWORD_GENERATOR = new PasswordGenerator();

    private static final List<String> USER_IDS = asList("midanque", "viwestwood", "rojohnson", "tpgolding");

    static Name createDomainName(PersonEntity entity) {
        return new Name(entity.getName().getFirstName(), entity.getName().getLastName());
    }

    final AuthenticatedUsers authenticatedUsers = new AuthenticatedUsers();

    final UserModels userModels = new UserModels(USER_IDS, PASSWORD_GENERATOR);

    final UserEntities userEntities = new UserEntities(USER_IDS, PASSWORD_GENERATOR);

    final List<UserData> userDataList = new ArrayList<>();

    public UserData getUserData(int index) { return userDataList.get(index); }

    void add(UserData userData) { userDataList.add(userData); }
}
