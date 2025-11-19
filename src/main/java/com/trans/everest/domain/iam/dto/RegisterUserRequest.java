package com.trans.everest.domain.iam.dto;

import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.SpecialiteType;
import com.trans.everest.domain.iam.model.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "the login is required")
        String login,
        @NotBlank(message = "The password is required")
        @Size(min = 6, message = "the password must be greater than 6 character")
        String password,
        @NotNull(message = "the role is required")
        RoleType role,
        UserStatus status,
        SpecialiteType specialite
) {
}
