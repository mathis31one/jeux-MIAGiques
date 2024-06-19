package com.miage.jeux_miagiques.service.DTOs;

import lombok.Data;

@Data
public class ParticipantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String adresseEmail;
    private Long delegationId;
}


