package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.repository.EpreuveRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    public Epreuve creerEpreuve(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }


    public void supprimerEpreuve(Long epreuveId) {
        epreuveRepository.deleteById(epreuveId);
    }

    public List<Epreuve> recupererToutesLesEpreuves() {
        return epreuveRepository.findAll();
    }

}
