package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Delegation;
import com.miage.jeux_miagiques.service.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delegation")
public class DelegationController {

    @Autowired
    private DelegationService delegationService;

  /*  // Création d'une délégation'
    @PostMapping("/creation")
    public ResponseEntity<Delegation> creerDelegation(@RequestParam String nom) {
        Delegation nouvelleDelegation = delegationService.inscrireDelegation(nom);
        return ResponseEntity.ok(nouvelleDelegation);
    }*/

    // Suppression d'une délégation
    @DeleteMapping("/suppression/{delegationId}")
    public ResponseEntity<?> supprimerDelegation(@PathVariable Long delegationId) {
        delegationService.supprimerDelegation(delegationId);
        return ResponseEntity.ok("Délégation supprimé avec succès");
    }

    //Récupérer tous les participants
    @GetMapping("/delegations")
    public ResponseEntity<List<Delegation>> recupererToutesLesDelegations() {
        List<Delegation> delegations = delegationService.recupererToutesLesDelegations();
        return ResponseEntity.ok(delegations);
    }
}
