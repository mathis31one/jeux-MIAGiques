package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Billet;
import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Participer;
import com.miage.jeux_miagiques.dao.repository.BilletRepository;
import com.miage.jeux_miagiques.dao.repository.EpreuveRepository;
import com.miage.jeux_miagiques.dao.repository.ParticiperRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;
    @Autowired
    private ParticiperRepository participerRepository;
    @Autowired
    private BilletRepository billetRepository;

    public Epreuve creerEpreuve(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }


    public void supprimerEpreuve(Long epreuveId) {
        //suppression des participations à cette épreuve
        List<Participer> participations = participerRepository.findByEpreuveId(epreuveId);
        for (Participer participation : participations) {
            participerRepository.delete(participation);
        }

        //suprression des billets de cette epreuve
        List<Billet> billets = billetRepository.findByEpreuveId(epreuveId);
        for (Billet billet : billets) {
            billetRepository.delete(billet);
        }

        //suppression de l'épreuve
        epreuveRepository.deleteById(epreuveId);
    }

    public List<Epreuve> recupererToutesLesEpreuves() {
        return epreuveRepository.findAll();
    }

}
