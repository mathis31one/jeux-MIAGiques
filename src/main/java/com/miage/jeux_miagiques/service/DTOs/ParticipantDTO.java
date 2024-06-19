package com.miage.jeux_miagiques.service.DTOs;

public class ParticipantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String adresseEmail;

    private Long delegationId;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public Long getDelegationId() {
        return delegationId;
    }
}


