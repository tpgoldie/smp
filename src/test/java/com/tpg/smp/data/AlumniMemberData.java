package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.persistence.entities.AlumniMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class AlumniMemberData extends UserData {
    private final AlumniMemberEntity alumniMemberEntity;

    public AlumniMemberData(AuthenticatedUser authenticatedUser, UserEntity userEntity,
                            AlumniMemberEntity alumniMemberEntity, UserModel userModel) {
        super(authenticatedUser, userEntity, userModel);

        this.alumniMemberEntity = alumniMemberEntity;
    }

    public AlumniMemberEntity getAlumniMemberEntity() {
        return alumniMemberEntity;
    }
}
