package com.miage.jeux_miagiques.service.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EpreuveDTO {
    private String nomEpreuve;
    private LocalDate date;
    private Long infrastructureId;
    private int nombrePlacesVente;
}
