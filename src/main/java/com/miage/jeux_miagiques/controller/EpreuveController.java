package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.service.DTOs.EpreuveDTO;
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
    public ResponseEntity<Epreuve> creationEpreuve(@RequestBody EpreuveDTO epreuveDTO) {
        Epreuve nouvelleEpreuve = epreuveService.creerEpreuve(epreuveDTO);
        return ResponseEntity.ok(nouvelleEpreuve);
    }

    // Suppression d'une épreuve
    @DeleteMapping("/suppression/{epreuveId}")
    public ResponseEntity<?> supprimerEpreuve(@PathVariable Long epreuveId) {
        epreuveService.supprimerEpreuve(epreuveId);
        return ResponseEntity.ok("Epreuve supprimée avec succès");
    }

    @GetMapping("/epreuves")
    public ResponseEntity<List<Epreuve>> recupererToutesLesEpreuves() {
        List<Epreuve> spectateurs = epreuveService.recupererToutesLesEpreuves();
        return ResponseEntity.ok(spectateurs);
    }

    @PutMapping("/capacite/{epreuveId}")
    public ResponseEntity<?> updatePlaces(@PathVariable Long epreuveId, @RequestParam int nbPlaces){
        Epreuve epreuve = epreuveService.definirPlaces(epreuveId,nbPlaces);
        return ResponseEntity.ok(epreuve);
    }
}
