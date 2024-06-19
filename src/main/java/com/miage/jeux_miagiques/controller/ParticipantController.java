package com.miage.jeux_miagiques.controller;
import java.util.Map;


import com.miage.jeux_miagiques.service.DTOs.ParticipantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miage.jeux_miagiques.dao.model.Participant;
import com.miage.jeux_miagiques.service.AccessTokenService;
import com.miage.jeux_miagiques.service.ParticipantService;


import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;
    
    @Autowired
    private AccessTokenService accessTokenService;

    // Création de compte participant
    @PostMapping("/inscription")
    public ResponseEntity<Participant> inscrireParticipant(@RequestBody ParticipantDTO participant) {
        Participant nouveauParticipant = participantService.inscrireParticipant(participant);
        return ResponseEntity.ok(nouveauParticipant);
    }

    // Suppression de compte de participant
    @DeleteMapping("/suppression/{participantId}")
    public ResponseEntity<?> supprimerParticipant(@PathVariable Long participantId) {
        participantService.supprimerParticipant(participantId);
        return ResponseEntity.ok("Compte de participant supprimé avec succès");
    }

    //Récupérer tous les participants
    @GetMapping("/participants")
    public ResponseEntity<List<Participant>> recupererTousLesParticipants() {
        List<Participant> participants = participantService.recupererTousLesParticipants();
        return ResponseEntity.ok(participants);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<?> loginParticipant(@RequestParam("email") String email, @RequestParam("role") String role) {
        String token = accessTokenService.generateToken(email, role);
        return ResponseEntity.ok(Map.of("token", token, "message", "Connexion réussie"));
    }

    @GetMapping("/check/connexion")
    public ResponseEntity<?> verifierConnexion(@RequestParam("email") String email) {
        String token = accessTokenService.getTokenByEmail(email);
        if (token != null && !accessTokenService.isTokenExpired(token)) {
            return ResponseEntity.ok("L'utilisateur est connecté.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("L'utilisateur n'est pas connecté ou le token est expiré.");
    }

    @PostMapping("/deconnexion")
    public ResponseEntity<?> deconnecterParticipant(@RequestParam("email") String email) {
        accessTokenService.invalidateToken(email);
        return ResponseEntity.ok("Déconnexion réussie.");
    }
}
