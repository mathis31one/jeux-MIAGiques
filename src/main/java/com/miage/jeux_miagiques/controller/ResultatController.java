package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Resultat;
import com.miage.jeux_miagiques.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resultat")
public class ResultatController {
    @Autowired
    private ResultatService resultatService;

    @GetMapping
    public ResponseEntity<List<Resultat>> getAllResultats() {
        List<Resultat> resultats = resultatService.getAllResultats();
        return ResponseEntity.ok(resultats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getResultatById(@PathVariable Long id) {
        Optional<Resultat> resultat = resultatService.getResultatById(id);
        return resultat.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resultat> createResultat(@RequestBody Resultat resultat) {
        Resultat createdResultat = resultatService.createOrUpdateResultat(resultat);
        return ResponseEntity.ok(createdResultat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateResultat(@PathVariable Long id, @RequestBody Resultat updatedResultat) {
        Optional<Resultat> existingResultat = resultatService.getResultatById(id);
        if (existingResultat.isPresent()) {
            updatedResultat.setId(id);
            Resultat savedResultat = resultatService.createOrUpdateResultat(updatedResultat);
            return ResponseEntity.ok(savedResultat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        Optional<Resultat> resultat = resultatService.getResultatById(id);
        if (resultat.isPresent()) {
            resultatService.deleteResultat(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
