package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Participer;
import com.miage.jeux_miagiques.service.DTOs.ParticiperDTO;
import com.miage.jeux_miagiques.service.ParticiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participer")
public class ParticiperController {
    @Autowired
    private ParticiperService participerService;

    // Inscription d'une nouvelle participation
    @PostMapping("/creation")
    public ResponseEntity<Participer> creationParticipation(@RequestBody ParticiperDTO participerDTO) {
        Participer nouvelleParticipation = participerService.creerParticipation(participerDTO);
        return ResponseEntity.ok(nouvelleParticipation);
    }

    // Suppression d'une participation
    @DeleteMapping("/desengager/{epreuveId}")
    public ResponseEntity<?> desengagerParticipation(@PathVariable Long partciperId) {
        participerService.supprimerParticipation(partciperId);
        return ResponseEntity.ok("participation supprimée avec succès");
    }

    @GetMapping("/participations")
    public ResponseEntity<List<Participer>> recupererToutesLesParticipations() {
        List<Participer> participations = participerService.recupererToutesLesParticipations();
        return ResponseEntity.ok(participations);
    }
}
