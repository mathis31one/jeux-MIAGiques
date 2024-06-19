package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Resultat;
import com.miage.jeux_miagiques.service.DTOs.ResultatDTO;
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
    public ResponseEntity<Resultat> createResultat(@RequestBody ResultatDTO resultatDTO) {
        Resultat createdResultat = resultatService.createResultat(resultatDTO);
        return ResponseEntity.ok(createdResultat);
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
