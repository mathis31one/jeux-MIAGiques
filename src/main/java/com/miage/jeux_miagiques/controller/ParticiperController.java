package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Participer;
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
    public ResponseEntity<Participer> creationParticipation(@RequestBody Participer participer) {
        Participer nouvelleParticipation = participerService.creerParticipation(participer);
        return ResponseEntity.ok(nouvelleParticipation);
    }

    // Suppression d'une participation
    @DeleteMapping("/suppression/{epreuveId}")
    public ResponseEntity<?> supprimerSpectateur(@PathVariable Long partciperId) {
        participerService.supprimerParticipation(partciperId);
        return ResponseEntity.ok("participation supprimée avec succès");
    }

    @GetMapping("/epreuves")
    public ResponseEntity<List<Participer>> recupererTousLesSpectateurs() {
        List<Participer> participations = participerService.recupererToutesLesParticipations();
        return ResponseEntity.ok(participations);
    }
}
