package com.miage.jeux_miagiques.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miage.jeux_miagiques.dao.model.Spectateur;
import com.miage.jeux_miagiques.service.SpectateurService;



@RestController
@RequestMapping("/spectateur")
public class SpectateurController {

	@Autowired
	private  SpectateurService spectateurService;
	
   
    // Inscription d'un nouveau spectateur
    @PostMapping("/inscription")
    public ResponseEntity<Spectateur> inscrireSpectateur(@RequestBody Spectateur spectateur) {
        Spectateur nouveauSpectateur = spectateurService.inscrireSpectateur(spectateur);
        return ResponseEntity.ok(nouveauSpectateur);
    }

    // Suppression de compte de spectateur
    @DeleteMapping("/suppression/{spectateurId}")
    public ResponseEntity<?> supprimerSpectateur(@PathVariable Long spectateurId) {
        spectateurService.supprimerSpectateur(spectateurId);
        return ResponseEntity.ok("Compte de spectateur supprimé avec succès");
    }
    
    @GetMapping("/spectateurs")
    public ResponseEntity<List<Spectateur>> recupererTousLesSpectateurs() {
        List<Spectateur> spectateurs = spectateurService.recupererTousLesSpectateurs();
        return ResponseEntity.ok(spectateurs);
    }

   

  

    
	
	
}
