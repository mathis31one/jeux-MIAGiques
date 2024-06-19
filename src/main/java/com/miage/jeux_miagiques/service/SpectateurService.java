package com.miage.jeux_miagiques.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Spectateur;
import com.miage.jeux_miagiques.dao.repository.BilletRepository;
import com.miage.jeux_miagiques.dao.repository.SpectateurRepository;

import lombok.Data;

@Service
public class SpectateurService {

	@Autowired
    private SpectateurRepository spectateurRepository;
	
	 @Autowired
	 private AccessTokenService accessTokenService;
	

    @Autowired
    private BilletRepository billetRepository;

    // Inscription d'un nouveau spectateur
    public Spectateur inscrireSpectateur(Spectateur spectateur) {
        return spectateurRepository.save(spectateur);
    }

    // Suppression de compte de spectateur
    public void supprimerSpectateur(Long spectateurId) {
        spectateurRepository.deleteById(spectateurId);
    }
    
    public List<Spectateur> recupererTousLesSpectateurs() {
        return spectateurRepository.findAll();
    }

        
    

    // Consulter le programme des épreuves
    public List<Epreuve> consulterProgramme() {
        // Implémenter la logique pour retourner le programme des épreuves
        return null;
    }

   
   
}
