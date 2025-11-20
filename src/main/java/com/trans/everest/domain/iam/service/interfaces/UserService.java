package com.trans.everest.domain.iam.service.interfaces;

import com.trans.everest.domain.iam.dto.RegisterUserRequest;
import com.trans.everest.domain.iam.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterUserRequest request);
}
