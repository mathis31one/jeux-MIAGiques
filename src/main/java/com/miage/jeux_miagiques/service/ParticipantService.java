package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Participant;
import com.miage.jeux_miagiques.dao.repository.ParticipantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;


    public Participant inscrireParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public void supprimerParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }

    public List<Participant> recupererTousLesParticipants() {
        return participantRepository.findAll();
    }
}
