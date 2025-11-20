package com.trans.everest.domain.iam.repository;

import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

    List<User> findByRole(RoleType role);
}
