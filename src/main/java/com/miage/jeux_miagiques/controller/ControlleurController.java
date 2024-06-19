package com.miage.jeux_miagiques.controller;


import com.miage.jeux_miagiques.dao.model.Controlleur;
import com.miage.jeux_miagiques.service.AccessTokenService;
import com.miage.jeux_miagiques.service.ControlleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controlleur")
public class ControlleurController {

    @Autowired
    private ControlleurService controlleurService;

    @Autowired
    private AccessTokenService accessTokenService;

    // Inscription d'un nouveau controlleur
    @PostMapping("/inscription")
    public ResponseEntity<Controlleur> inscrireControlleur(@RequestBody Controlleur controlleur) {
        Controlleur nouveauControlleur = controlleurService.inscrireControlleur(controlleur);
        return ResponseEntity.ok(nouveauControlleur);
    }

    // Suppression de compte de controlleur
    @DeleteMapping("/suppression/{controlleurId}")
    public ResponseEntity<?> supprimerControlleur(@PathVariable Long controlleurId) {
        controlleurService.supprimerControlleur(controlleurId);
        return ResponseEntity.ok("Compte de controlleur supprimé avec succès");
    }

    // Récupérer tous les controlleurs
    @GetMapping("/controlleurs")
    public ResponseEntity<List<Controlleur>> recupererTousLesControlleurs() {
        List<Controlleur> controlleurs = controlleurService.recupererTousLesControlleurs();
        return ResponseEntity.ok(controlleurs);
    }

    // Connexion d'un controlleur
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("email") String email, @RequestParam("role") String role) {
        String token = accessTokenService.generateToken(email, role);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Connexion réussie !");
        return ResponseEntity.ok(response);
    }

    // Vérifier la connexion d'un controlleur
    @GetMapping("/check/connexion")
    public ResponseEntity<?> verifierConnexion(@RequestParam("email") String email) {
        String token = accessTokenService.getTokenByEmail(email);
        if (token != null && !accessTokenService.isTokenExpired(token)) {
            return ResponseEntity.ok("L'utilisateur est connecté.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("L'utilisateur n'est pas connecté ou le token est expiré.");
    }

    // Déconnexion d'un controlleur
    @PostMapping("/deconnexion")
    public ResponseEntity<?> deconnecterControlleur(@RequestParam("email") String email) {
        accessTokenService.invalidateToken(email);
        return ResponseEntity.ok("Déconnexion réussie.");
    }
}
