package com.trans.everest.domain.logistics.repository;

import com.trans.everest.domain.logistics.enums.ColisStatus;
import com.trans.everest.domain.logistics.model.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ColisRepository extends MongoRepository<Colis, String> {
    Optional<Colis> findByCodeTracking(String codeTracking);

    Page<Colis> findAllByStatus(ColisStatus status, Pageable pageable);

    Page<Colis> findAllByLivreurId(String livreurId, Pageable pageable);

    Page<Colis> findAllByLivreurIdAndStatus(String livreurId, ColisStatus status, Pageable pageable);

    Page<Colis> findAllByAdresseDestinationContainingIgnoreCase(String adresse, Pageable pageable);

    Page<Colis> findAllByLivreurIdAndAdresseDestinationContainingIgnoreCase(String livreurId, String adresse, Pageable pageable);
}
