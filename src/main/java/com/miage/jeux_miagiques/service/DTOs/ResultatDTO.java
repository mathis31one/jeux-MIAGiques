package com.miage.jeux_miagiques.service.DTOs;

import lombok.Data;

@Data
public class ResultatDTO {
    private long epreuveId;
    private long participantId;
    private int tempsPoints;
}
