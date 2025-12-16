package com.trans.everest.domain.iam.service;

import com.trans.everest.domain.iam.dto.RegisterUserRequest;
import com.trans.everest.domain.iam.dto.UpdateTransporteurRequest;
import com.trans.everest.domain.iam.dto.UserResponse;
import com.trans.everest.domain.iam.mapper.UserMapper;
import com.trans.everest.domain.iam.model.User;
import com.trans.everest.domain.iam.repository.UserRepository;
import com.trans.everest.domain.iam.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse registerUser(RegisterUserRequest request) {
        if(userRepository.existsByLogin(request.login())){
            throw new RuntimeException("this login is already used");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }



    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserResponse updateTransporteur(String id, UpdateTransporteurRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé"));

        if (request.login() != null && !request.login().isBlank()) {
            if (!user.getLogin().equals(request.login()) && userRepository.existsByLogin(request.login())) {
                throw new RuntimeException("Ce login est déjà utilisé !");
            }
            user.setLogin(request.login());
        }

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        if (request.specialite() != null) {
            user.setSpecialite(request.specialite());
        }

        if (request.statut() != null) {
            user.setStatut(request.statut());
        }

        return userMapper.toResponse(userRepository.save(user));
    }
}
