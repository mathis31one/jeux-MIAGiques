package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Participant;
import com.miage.jeux_miagiques.dao.model.Participer;
import com.miage.jeux_miagiques.dao.repository.EpreuveRepository;
import com.miage.jeux_miagiques.dao.repository.ParticipantRepository;
import com.miage.jeux_miagiques.dao.repository.ParticiperRepository;
import com.miage.jeux_miagiques.service.DTOs.ParticiperDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ParticiperService {

    @Autowired
    private ParticiperRepository participerRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private EpreuveRepository epreuveRepository;

    public Participer creerParticipation(ParticiperDTO participerDTO) {
        Participer participer = new Participer();

        Participant participant = participantRepository.findById(participerDTO.getParticipantID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid participant ID"));

        Epreuve epreuve = epreuveRepository.findById(participerDTO.getEpreuveID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid epreuve ID"));


        List<Participer> participations = participerRepository.findByEpreuveId(participerDTO.getEpreuveID());

        int participantsMaxEpreuve = participer.getEpreuve().getNombreParticipantsMaximum();
        int participantsEpreuve = participations.size();
        if(participantsEpreuve >= participantsMaxEpreuve){
            throw new IllegalStateException("l'épreuve est déjà à son nombre maximum de participants");
        }
        
        for(Participer participation : participations) {
            if(participation.getParticipant().getDelegation() == participant.getDelegation()){
                throw new IllegalStateException("Un participant de cette délégation est déjà inscrit");
            }
        }

        participer.setStatus("Actif");
        participer.setParticipant(participant);
        participer.setEpreuve(epreuve);
        return participerRepository.save(participer);
    }

    public void supprimerParticipation(Long participerId) {
        Participer participer = participerRepository.findById(participerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid participer ID"));
        LocalDate today = LocalDate.now();
        if (participer.getEpreuve().getDate().isBefore(today.plusDays(10))) {
            participer.setStatus("Forfait");
            participerRepository.save(participer);
        }else {
            participerRepository.deleteById(participerId);
        }
    }

    public List<Participer> recupererToutesLesParticipations() {
        return participerRepository.findAll();
    }
}
