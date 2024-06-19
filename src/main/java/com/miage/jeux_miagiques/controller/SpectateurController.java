package com.miage.jeux_miagiques.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.miage.jeux_miagiques.dao.model.Spectateur;
import com.miage.jeux_miagiques.service.AccessTokenService;
import com.miage.jeux_miagiques.service.SpectateurService;




@RestController
@RequestMapping("/spectateur")
public class SpectateurController {

	@Autowired
	private  SpectateurService spectateurService;
	
	 @Autowired
	 private AccessTokenService accessTokenService;

	
   
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

    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("email") String email, @RequestParam("role") String role) {
        String token = accessTokenService.generateToken(email, role);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Connexion réussie !");
        return ResponseEntity.ok(response);
    }
   
    @GetMapping("check/connexion")
    public ResponseEntity<?> verifierConnexion(@RequestParam("email") String email) {
        String token = accessTokenService.getTokenByEmail(email);
        if (token != null && !accessTokenService.isTokenExpired(token)) {
            return ResponseEntity.ok("L'utilisateur est connecté.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("L'utilisateur n'est pas connecté ou le token est expiré.");
    }
    
    @PostMapping("/deconnexion")
    public ResponseEntity<?> deconnecterSpectateur(@RequestParam("email") String email) {
        accessTokenService.invalidateToken(email);
        return ResponseEntity.ok("Déconnexion réussie.");
    }
    
   }
       
  
