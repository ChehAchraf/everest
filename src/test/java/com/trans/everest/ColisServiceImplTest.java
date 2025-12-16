package com.trans.everest;

import com.trans.everest.domain.iam.model.RoleType;
import com.trans.everest.domain.iam.model.SpecialiteType;
import com.trans.everest.domain.iam.model.User;
import com.trans.everest.domain.iam.repository.UserRepository;

import com.trans.everest.domain.logistics.enums.ColisStatus;
import com.trans.everest.domain.logistics.enums.ColisType;
import com.trans.everest.domain.logistics.model.Colis;
import com.trans.everest.domain.logistics.repository.ColisRepository;
import com.trans.everest.domain.logistics.service.ColisServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColisServiceImplTest {

    @Mock
    private ColisRepository colisRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ColisServiceImpl colisService;


    @Test
    void assignColis_ShouldSucceed_WhenSpecialitiesMatch() {

        String colisId = "colis123";
        String livreurId = "livreur123";

        Colis colis = Colis.builder()
                .id(colisId)
                .type(ColisType.FRIGO) // كولية فريكو
                .status(ColisStatus.EN_ATTENTE)
                .build();

        User livreur = User.builder()
                .id(livreurId)
                .role(RoleType.TRANSPORTEUR)
                .specialite(SpecialiteType.FRIGO) // ليفرور فريكو
                .build();

        when(colisRepository.findById(colisId)).thenReturn(Optional.of(colis));
        when(userRepository.findById(livreurId)).thenReturn(Optional.of(livreur));
        when(colisRepository.save(any(Colis.class))).thenReturn(colis);

        colisService.assignColisToTransporter(colisId, livreurId);

        assertEquals(livreurId, colis.getLivreur().getId());
        assertEquals(ColisStatus.EN_TRANSIT, colis.getStatus());
        verify(colisRepository).save(colis);
    }


    @Test
    void assignColis_ShouldThrowException_WhenSpecialityMismatch() {
        // 1. Arrange
        String colisId = "colisFragile";
        String livreurId = "livreurStandard";

        Colis colis = Colis.builder()
                .id(colisId)
                .type(ColisType.FRAGILE)
                .build();

        User livreur = User.builder()
                .id(livreurId)
                .role(RoleType.TRANSPORTEUR)
                .specialite(SpecialiteType.STANDARD)
                .build();

        when(colisRepository.findById(colisId)).thenReturn(Optional.of(colis));
        when(userRepository.findById(livreurId)).thenReturn(Optional.of(livreur));

        assertThrows(RuntimeException.class, () -> {
            colisService.assignColisToTransporter(colisId, livreurId);
        });

        verify(colisRepository, never()).save(any(Colis.class));
    }
}