package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Participant;
import com.miage.jeux_miagiques.dao.model.Resultat;
import com.miage.jeux_miagiques.dao.repository.DelegationRepository;
import com.miage.jeux_miagiques.dao.repository.EpreuveRepository;
import com.miage.jeux_miagiques.dao.repository.ParticipantRepository;
import com.miage.jeux_miagiques.dao.repository.ResultatRepository;
import com.miage.jeux_miagiques.service.DTOs.ResultatDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;
    @Autowired
    private EpreuveRepository epreuveRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private DelegationRepository delegationRepository;

    public List<Resultat> getAllResultats() {
        return resultatRepository.findAll();
    }

    public Optional<Resultat> getResultatById(Long id) {
        return resultatRepository.findById(id);
    }

    public Resultat createResultat(ResultatDTO resultatDTO) {
        Epreuve epreuve = epreuveRepository.findById(resultatDTO.getEpreuveId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid epreuve ID"));

        Participant participant = participantRepository.findById(resultatDTO.getParticipantId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid participant ID"));



        Resultat resultat = new Resultat();
        resultat.setEpreuve(epreuve);
        resultat.setParticipant(participant);
        resultat.setTempsPoints(resultatDTO.getTempsPoints());
        resultat = resultatRepository.save(resultat);
        long resultatId = resultat.getId();


        calculCalssement(resultatDTO.getEpreuveId());

        Resultat resultatFinal = resultatRepository.findById(resultatId).orElse(resultat);
        return resultatFinal;
    }

    public void deleteResultat(Long id) {
        resultatRepository.deleteById(id);
    }


    private void calculCalssement(Long epreuveId){
        List<Resultat> resultats = resultatRepository.findAllByEpreuve_Id(epreuveId);
        resultats.sort(Comparator.comparingInt(Resultat::getTempsPoints));
        for (int i = 0; i < resultats.size(); i++) {
            Resultat resultat = resultats.get(i);
            switch (resultat.getPosition()){
                case 1:
                    resultat.getParticipant().getDelegation().setNombreMedaillesOr(resultat.getParticipant().getDelegation().getNombreMedaillesOr() - 1);
                    break;
                case 2:
                    resultat.getParticipant().getDelegation().setNombreMedaillesArgent(resultat.getParticipant().getDelegation().getNombreMedaillesArgent() - 1);
                    break;
                case 3:
                    resultat.getParticipant().getDelegation().setNombreMedaillesBronze(resultat.getParticipant().getDelegation().getNombreMedaillesBronze() - 1);
                    break;
            }

            resultat.setPosition(i + 1);
            switch (i+1){
                case 1:
                    resultat.getParticipant().getDelegation().setNombreMedaillesOr(resultat.getParticipant().getDelegation().getNombreMedaillesOr() + 1);
                    break;
                case 2:
                    resultat.getParticipant().getDelegation().setNombreMedaillesArgent(resultat.getParticipant().getDelegation().getNombreMedaillesArgent() + 1);
                    break;
                case 3:
                    resultat.getParticipant().getDelegation().setNombreMedaillesBronze(resultat.getParticipant().getDelegation().getNombreMedaillesBronze() + 1);
                    break;
            }
        }
        resultatRepository.saveAll(resultats);
    }
}
