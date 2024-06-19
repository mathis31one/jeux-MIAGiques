package com.miage.jeux_miagiques.service.DTOs;

import com.miage.jeux_miagiques.dao.model.Billet;
import lombok.Data;

@Data
public class BilletDTO {
    private Long epreuveId;
    private Long spectateurId;
    private double prix;
    private Billet.EtatBillet etat;
}
