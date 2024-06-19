package com.miage.jeux_miagiques.service.DTOs;

import java.time.LocalDate;

public class EpreuveDTO {
    private String nomEpreuve;
    private LocalDate date;
    private Long infrastructureId;
    private int nombrePlacesVente;

    public String getNomEpreuve() {
        return nomEpreuve;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getInfrastructureId() {
        return infrastructureId;
    }

    public int getNombrePlacesVente() {
        return nombrePlacesVente;
    }
}
