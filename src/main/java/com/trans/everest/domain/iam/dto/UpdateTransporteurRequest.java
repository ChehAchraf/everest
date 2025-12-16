package com.trans.everest.domain.iam.dto;

import com.trans.everest.domain.iam.model.SpecialiteType;
import com.trans.everest.domain.iam.model.UserStatus;

public record UpdateTransporteurRequest(
        String login,
        String password,
        SpecialiteType specialite,
        UserStatus statut
) {
}
