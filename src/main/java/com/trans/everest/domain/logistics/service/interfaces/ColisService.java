package com.trans.everest.domain.logistics.service.interfaces;

import com.trans.everest.domain.logistics.dto.ColisRequest;
import com.trans.everest.domain.logistics.enums.ColisStatus;
import com.trans.everest.domain.logistics.model.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColisService {
    Colis createColis(ColisRequest request);

    Colis assignColisToTransporter(String colisId, String transporterId);

    Colis updateStatus(String colisId, ColisStatus newStatus);

    Page<Colis> getAllColis(Pageable pageable);
    Page<Colis> getColisByTransporter(String transporterId, Pageable pageable);

    Page<Colis> searchByCity(String city, Pageable pageable);
}
