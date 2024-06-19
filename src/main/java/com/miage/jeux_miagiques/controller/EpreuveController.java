package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epreuve")
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;


    // Inscription d'une nouvelle epreuve
    @PostMapping("/creation")
    public ResponseEntity<Epreuve> creationEpreuve(@RequestBody Epreuve epreuve) {
        Epreuve nouvelleEpreuve = epreuveService.creerEpreuve(epreuve);
        return ResponseEntity.ok(nouvelleEpreuve);
    }

    // Suppression d'une épreuve
    @DeleteMapping("/suppression/{epreuveId}")
    public ResponseEntity<?> supprimerSpectateur(@PathVariable Long epreuveId) {
        epreuveService.supprimerEpreuve(epreuveId);
        return ResponseEntity.ok("Compte de spectateur supprimé avec succès");
    }

    @GetMapping("/epreuves")
    public ResponseEntity<List<Epreuve>> recupererTousLesSpectateurs() {
        List<Epreuve> spectateurs = epreuveService.recupererToutesLesEpreuves();
        return ResponseEntity.ok(spectateurs);
    }
}