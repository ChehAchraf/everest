package com.trans.everest.domain.logistics.service;

import com.trans.everest.domain.iam.repository.UserRepository;
import com.trans.everest.domain.logistics.dto.ColisRequest;
import com.trans.everest.domain.logistics.enums.ColisStatus;
import com.trans.everest.domain.logistics.enums.ColisType;
import com.trans.everest.domain.logistics.model.Colis;
import com.trans.everest.domain.logistics.repository.ColisRepository;
import com.trans.everest.domain.logistics.service.interfaces.ColisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColisServiceImpl implements ColisService {

    private final ColisRepository colisRepository;
    private final UserRepository userRepository;

    @Override
    public Colis createColis(ColisRequest request) {
        Colis colis = Colis.builder()
                .codeTracking(generateTrackingCode())
                .poids(request.poids())
                .adresseDestination(request.adresseDestination())
                .adresseDepart(request.adresseDepart())
                .type(request.type())
                .status(ColisStatus.EN_ATTENTE)

                .instructionsManutention(request.type() == ColisType.FRAGILE ? request.instructionsManutention() : null)
                .temperatureMin(request.type() == ColisType.FRIGO ? request.temperatureMin() : null)
                .temperatureMax(request.type() == ColisType.FRIGO ? request.temperatureMax() : null)
                .build();

        return colisRepository.save(colis);
    }

    @Override
    public Colis assignColisToTransporter(String colisId, String transporterId) {
        return null;
    }

    @Override
    public Colis updateStatus(String colisId, ColisStatus newStatus) {
        return null;
    }

    @Override
    public Page<Colis> getAllColis(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Colis> getColisByTransporter(String transporterId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Colis> searchByCity(String city, Pageable pageable) {
        return null;
    }

    private String generateTrackingCode() {
        return "EV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
