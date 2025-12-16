package com.trans.everest.domain.iam.service.interfaces;

import com.trans.everest.domain.iam.dto.RegisterUserRequest;
import com.trans.everest.domain.iam.dto.UpdateTransporteurRequest;
import com.trans.everest.domain.iam.dto.UserResponse;
import com.trans.everest.domain.iam.model.User;

import java.util.Optional;

public interface UserService {
    UserResponse registerUser(RegisterUserRequest request);

    Optional<User> findByLogin(String login);

    public UserResponse updateTransporteur(String id, UpdateTransporteurRequest request);
}
