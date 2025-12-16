package com.trans.everest.domain.iam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection  = "users")
public class User {
    @Id
    private String id;

    private String login;
    @JsonIgnore
    private String password;
    private RoleType role;
    private boolean active;

    private UserStatus statut;
    private SpecialiteType specialite;
}
