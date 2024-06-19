package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.Spectateur;
import com.miage.jeux_miagiques.service.DTOs.ParticipantDTO;
import com.miage.jeux_miagiques.service.SpectateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miage.jeux_miagiques.dao.model.Participant;
import com.miage.jeux_miagiques.service.ParticipantService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

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
}
