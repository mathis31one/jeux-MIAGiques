package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Billet;
import com.miage.jeux_miagiques.service.BilletService;
import com.miage.jeux_miagiques.service.DTOs.BilletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/billets")
public class BilletController {
    @Autowired
    private BilletService billetService;

    @PostMapping("/reserver")
    public ResponseEntity<Billet> reserverBillet(@RequestBody BilletDTO billetDTO) {
        Billet billet = billetService.reserverBillet(billetDTO);
        return ResponseEntity.ok(billet);
    }

    @DeleteMapping("/annuler/{billetId}")
    public ResponseEntity<String> annulerBillet(@PathVariable Long billetId) {
        try {
            billetService.annulerBillet(billetId);
            return ResponseEntity.ok("Annulation confirmée");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/spectateur/{spectateurId}")
    public ResponseEntity<List<Billet>> recupererTousLesBilletParSpectateur(@PathVariable Long spectateurId) {
        List<Billet> billets = billetService.recupererTousLesBilletParSpectateur(spectateurId);
        return ResponseEntity.ok(billets);
    }

    @GetMapping("/epreuve/{epreuveId}")
    public ResponseEntity<List<Billet>> recupererTousLesBilletParEpreuve(@PathVariable Long epreuveId) {
        List<Billet> billets = billetService.recupererTousLesBilletParEpreuve(epreuveId);
        return ResponseEntity.ok(billets);
    }

    @GetMapping
    public ResponseEntity<List<Billet>> recupererTousLesBillets() {
        List<Billet> billets = billetService.recupererTousLesBille();
        return ResponseEntity.ok(billets);
    }
}
