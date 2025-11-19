package com.trans.everest.domain.iam.mapper;

import com.trans.everest.domain.iam.dto.RegisterUserRequest;
import com.trans.everest.domain.iam.dto.UserResponse;
import com.trans.everest.domain.iam.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "active",constant = "true")
    User toEntity(RegisterUserRequest request);

    UserResponse toRequest(User user);

}
