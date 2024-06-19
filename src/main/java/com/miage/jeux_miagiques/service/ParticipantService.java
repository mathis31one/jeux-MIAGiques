package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Delegation;
import com.miage.jeux_miagiques.dao.model.Participant;
import com.miage.jeux_miagiques.dao.repository.DelegationRepository;
import com.miage.jeux_miagiques.dao.repository.ParticipantRepository;
import com.miage.jeux_miagiques.service.DTOs.ParticipantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private DelegationRepository delegationRepository;


    public Participant inscrireParticipant(ParticipantDTO participantDTO) {
        Delegation delegation = delegationRepository.findById(participantDTO.getDelegationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid delegation ID"));
        Participant participant = new Participant();
        participant.setDelegation(delegation);
        participant.setNom(participantDTO.getNom());
        participant.setPrenom(participantDTO.getPrenom());
        participant.setAdresseEmail(participantDTO.getAdresseEmail());
        return participantRepository.save(participant);
    }

    public void supprimerParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }

    public List<Participant> recupererTousLesParticipants() {
        return participantRepository.findAll();
    }
}
