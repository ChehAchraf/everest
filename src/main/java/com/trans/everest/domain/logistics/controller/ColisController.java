package com.trans.everest.domain.logistics.controller;

import com.trans.everest.domain.iam.model.User;
import com.trans.everest.domain.iam.service.interfaces.UserService;
import com.trans.everest.domain.logistics.dto.ColisRequest;
import com.trans.everest.domain.logistics.model.Colis;
import com.trans.everest.domain.logistics.service.interfaces.ColisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColisController {
    private final ColisService colisService;
    private final UserService userService;

    @PostMapping("/admin/colis")
    public ResponseEntity<Colis> createColis(@RequestBody ColisRequest request) {
        return ResponseEntity.ok(colisService.createColis(request));
    }

    @PutMapping("/admin/colis/{id}/assign")
    public ResponseEntity<Colis> assignColis(@PathVariable String id, @RequestParam String transporterId) {
        return ResponseEntity.ok(colisService.assignColisToTransporter(id, transporterId));
    }

    public ResponseEntity<Page<Colis>> getAllColis(Pageable pageable) {
        return ResponseEntity.ok(colisService.getAllColis(pageable));
    }

    @GetMapping("/transporteur/colis")
    public ResponseEntity<Page<Colis>> getMyColis(Pageable pageable) {
        User currentUser = getCurrentUser();
        return ResponseEntity.ok(colisService.getColisByTransporter(currentUser.getId(), pageable));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userService.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
