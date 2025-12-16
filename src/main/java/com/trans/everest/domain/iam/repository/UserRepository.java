package com.trans.everest.domain.iam.repository;

import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.SpecialiteType;
import com.trans.everest.domain.iam.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

    Page<User> findByRole(RoleType role, Pageable pageable);

    Page<User> findByRoleAndSpecialite(RoleType role, SpecialiteType specialite, Pageable pageable);
}
