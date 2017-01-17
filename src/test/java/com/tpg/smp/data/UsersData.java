package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.UserEntities;
import com.tpg.smp.web.model.UserModels;

import java.util.ArrayList;
import java.util.List;

public class UsersData {
    final UserModels userModels = new UserModels();

    final UserEntities userEntities = new UserEntities();

    final List<UserData> userDataList = new ArrayList<>();

    public UserData getUserData(int index) { return userDataList.get(index); }
}
