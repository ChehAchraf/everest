package com.trans.everest.domain.iam.dto;

import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.SpecialiteType;
import com.trans.everest.domain.iam.model.UserStatus;

public record UserResponse(
        String id,
        String login,
        RoleType role,
        boolean active,
        UserStatus statut,
        SpecialiteType specialite
) {
}
