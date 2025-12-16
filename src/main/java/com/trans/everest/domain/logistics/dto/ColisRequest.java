package com.trans.everest.domain.logistics.dto;

import com.trans.everest.domain.logistics.enums.ColisType;
import jakarta.validation.constraints.NotNull;

public record ColisRequest(
        @NotNull(message = "Le poids est obligatoire")
        Double poids,

        @NotNull(message = "L'adresse de destination est obligatoire")
        String adresseDestination,

        String adresseDepart,

        @NotNull(message = "Le type de colis est obligatoire")
        ColisType type,

        // حقول خاصة (Optional)
        String instructionsManutention,
        Double temperatureMin,
        Double temperatureMax
) {
}
