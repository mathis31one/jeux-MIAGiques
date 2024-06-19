package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Controlleur;
import com.miage.jeux_miagiques.dao.repository.ControlleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlleurService {

    @Autowired
    private ControlleurRepository controlleurRepository;

    public Controlleur inscrireControlleur(Controlleur controlleur) {
        return controlleurRepository.save(controlleur);
    }

    public void supprimerControlleur(Long controlleurId) {
        controlleurRepository.deleteById(controlleurId);
    }

    public List<Controlleur> recupererTousLesControlleurs() {
        return controlleurRepository.findAll();
    }

}
