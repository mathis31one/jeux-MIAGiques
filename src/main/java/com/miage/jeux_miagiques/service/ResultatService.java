package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Resultat;
import com.miage.jeux_miagiques.dao.repository.ResultatRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;

    public List<Resultat> getAllResultats() {
        return resultatRepository.findAll();
    }

    public Optional<Resultat> getResultatById(Long id) {
        return resultatRepository.findById(id);
    }

    public Resultat createOrUpdateResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public void deleteResultat(Long id) {
        resultatRepository.deleteById(id);
    }
}
