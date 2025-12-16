package com.trans.everest.domain.logistics.model;

import com.trans.everest.domain.iam.model.User;
import com.trans.everest.domain.logistics.enums.ColisStatus;
import com.trans.everest.domain.logistics.enums.ColisType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "colis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Colis {
    @Id
    private String id;

    private String codeTracking;

    private double poids;
    private String adresseDestination;
    private String adresseDepart;

    private ColisStatus status; // [cite: 16]
    private ColisType type;     // STANDARD, FRAGILE, FRIGO

    private String instructionsManutention;

    private Double temperatureMin;
    private Double temperatureMax;

    @DBRef
    private User livreur;

    @Builder.Default
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateLivraison;
}
