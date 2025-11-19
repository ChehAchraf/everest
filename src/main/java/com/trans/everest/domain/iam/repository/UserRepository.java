package com.trans.everest.domain.iam.repository;

import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    boolean findByLogin(String login);

    boolean existsByLogin(String login);

    List<User> findByRole(RoleType role);
}
