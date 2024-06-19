package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Billet;
import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.InfrastructureSportive;
import com.miage.jeux_miagiques.dao.model.Participer;
import com.miage.jeux_miagiques.dao.repository.BilletRepository;
import com.miage.jeux_miagiques.dao.repository.EpreuveRepository;
import com.miage.jeux_miagiques.dao.repository.InfrastructureSportiveRepository;
import com.miage.jeux_miagiques.dao.repository.ParticiperRepository;
import com.miage.jeux_miagiques.service.DTOs.EpreuveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;
    @Autowired
    private ParticiperRepository participerRepository;
    @Autowired
    private BilletRepository billetRepository;
    @Autowired
    private InfrastructureSportiveRepository infrastructureSportiveRepository;

    public Epreuve creerEpreuve(EpreuveDTO epreuveDTO) {
        InfrastructureSportive infrastructureSportive = infrastructureSportiveRepository.findById(epreuveDTO.getInfrastructureId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid infrastucture ID"));
        Epreuve epreuve = new Epreuve();
        epreuve.setNombrePlacesVente(epreuveDTO.getNombrePlacesVente());
        epreuve.setNomEpreuve(epreuveDTO.getNomEpreuve());
        epreuve.setDate(epreuveDTO.getDate());
        epreuve.setInfrastructureSportive(infrastructureSportive);
        return epreuveRepository.save(epreuve);
    }


    public void supprimerEpreuve(Long epreuveId) {
        //suppression des participations à cette épreuve
        List<Participer> participations = participerRepository.findByEpreuveId(epreuveId);
        for (Participer participation : participations) {
            participerRepository.delete(participation);
        }

        //suprression des billets de cette epreuve
        List<Billet> billets = billetRepository.findBilletByEpreuve_Id(epreuveId);
        for (Billet billet : billets) {
            billetRepository.delete(billet);
        }

        //suppression de l'épreuve
        epreuveRepository.deleteById(epreuveId);
    }

    public Epreuve definirPlaces(Long epreuveId, int places) {
        Epreuve epreuve = epreuveRepository.findById(epreuveId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid epreuve ID"));
        int capaciteMax = epreuve.getInfrastructureSportive().getCapacite();
        if(places > capaciteMax){
            throw new IllegalStateException("la capacité de l'infrastructure ne peut permettre autant de places en vente");
        }else{
            epreuve.setNombrePlacesVente(places);
        }
        return epreuveRepository.save(epreuve);
    }

    public Epreuve definirParticipantsMax(long epreuveId, int nombreMax) {
        Epreuve epreuve = epreuveRepository.findById(epreuveId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid epreuve ID"));
        epreuve.setNombreParticipantsMaximum(nombreMax);
        return epreuveRepository.save(epreuve);
    }

    public List<Epreuve> recupererToutesLesEpreuves() {
        return epreuveRepository.findAll();
    }

}
