package com.trans.everest.domain.iam.controller;

import com.trans.everest.domain.iam.dto.RegisterUserRequest;
import com.trans.everest.domain.iam.dto.UpdateTransporteurRequest;
import com.trans.everest.domain.iam.dto.UserResponse;
import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.SpecialiteType;
import com.trans.everest.domain.iam.model.User;
import com.trans.everest.domain.iam.model.UserStatus;
import com.trans.everest.domain.iam.repository.UserRepository;
import com.trans.everest.domain.iam.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/transporteurs")
    public ResponseEntity<Page<User>> getTransporteurs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) SpecialiteType specialite
    ) {
        PageRequest pageable = PageRequest.of(page, size);

        if (specialite != null) {
            return ResponseEntity.ok(userRepository.findByRoleAndSpecialite(RoleType.TRANSPORTEUR, specialite, pageable));
        }
        return ResponseEntity.ok(userRepository.findByRole(RoleType.TRANSPORTEUR, pageable));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(userRepository.findAll(PageRequest.of(page, size)));
    }

    @PatchMapping("/users/{id}/status")
    public ResponseEntity<User> toggleUserStatus(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        user.setActive(!user.isActive());

        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transporteurs")
    public ResponseEntity<UserResponse> createTransporteur(@Valid @RequestBody RegisterUserRequest request) {
        if (request.specialite() == null) {
            throw new IllegalArgumentException("La spécialité est obligatoire pour un transporteur");
        }
        RegisterUserRequest newRequest = new RegisterUserRequest(
                request.login(),
                request.password(),
                RoleType.TRANSPORTEUR,
                UserStatus.DISPONIBLE,
                request.specialite()
        );

        return ResponseEntity.ok(userService.registerUser(newRequest));
    }

    @PutMapping("/transporteurs/{id}")
    public ResponseEntity<UserResponse> updateTransporteur(
            @PathVariable String id,
            @RequestBody UpdateTransporteurRequest request
    ) {
        return ResponseEntity.ok(userService.updateTransporteur(id, request));
    }

}
