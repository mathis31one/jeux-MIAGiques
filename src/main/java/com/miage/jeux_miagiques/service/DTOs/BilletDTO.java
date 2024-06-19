package com.miage.jeux_miagiques.service.DTOs;

import com.miage.jeux_miagiques.dao.model.Billet;

public class BilletDTO {
    private Long epreuveId;
    private Long spectateurId;
    private double prix;
    private Billet.EtatBillet etat;


    public Long getEpreuveId() {
        return epreuveId;
    }

    public Long getSpectateurId() {
        return spectateurId;
    }

    public double getPrix() {
        return prix;
    }

    public Billet.EtatBillet getEtat() {
        return etat;
    }
}
