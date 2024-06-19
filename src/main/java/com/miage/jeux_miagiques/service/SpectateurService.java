package com.miage.jeux_miagiques.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Spectateur;
import com.miage.jeux_miagiques.dao.repository.BilletRepository;
import com.miage.jeux_miagiques.dao.repository.SpectateurRepository;

import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Service
public class SpectateurService {
	@Autowired
    private SpectateurRepository spectateurRepository;

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

  
    

    // Réservation des billets
/*    @Transactional
    public Billet reserverBillet(Long spectateurId, Long epreuveId, double prix) {
        Billet billet = new Billet();
        billet.epreuveId
        billet.setSpectateurId(spectateurId);
        billet.setPrix(prix);
        billet.setEtat(Billet.EtatBillet.VALIDE);
        return billetRepository.save(billet);
    }*/

    // Annuler une réservation
   /* public void annulerReservation(Long billetId) {
        Billet billet = billetRepository.findById(billetId).orElseThrow();
        billet.setEtat(Billet.EtatBillet.ANNULE);
        billetRepository.save(billet);
    }*/

    // Consulter le programme des épreuves
    public List<Epreuve> consulterProgramme() {
        // Implémenter la logique pour retourner le programme des épreuves
        return null;
    }

   
   
}
