package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Billet;
import com.miage.jeux_miagiques.dao.model.Epreuve;
import com.miage.jeux_miagiques.dao.model.Spectateur;
import com.miage.jeux_miagiques.dao.repository.BilletRepository;
import com.miage.jeux_miagiques.dao.repository.EpreuveRepository;
import com.miage.jeux_miagiques.dao.repository.SpectateurRepository;
import com.miage.jeux_miagiques.service.DTOs.BilletDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Service
public class BilletService {

    @Autowired
    BilletRepository billetRepository;
    @Autowired
    SpectateurRepository spectateurRepository;
    @Autowired
    EpreuveRepository epreuveRepository;

    public Billet reserverBillet(BilletDTO billetDTO){
        Spectateur spectateur = spectateurRepository.findById(billetDTO.getSpectateurId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid spectateur ID"));
        Epreuve epreuve = epreuveRepository.findById(billetDTO.getEpreuveId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid spectateur ID"));


        Billet billet = new Billet();
        billet.setSpectateur(spectateur);
        billet.setEpreuve(epreuve);
        billet.setEtat(billetDTO.getEtat());
        billet.setPrix(billetDTO.getPrix());


        epreuve.setNombrePlacesVente(epreuve.getNombrePlacesVente() - 1);
        return billetRepository.save(billet);
    }


    public void annulerBillet(Long billetId) {
        Billet billet = billetRepository.findById(billetId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid billet ID"));
        Epreuve epreuve = billet.getEpreuve();
        LocalDate dateEpreuve = epreuve.getDate();
        LocalDate today = LocalDate.now();
        Period periodBetween = Period.between(today, dateEpreuve);

        if (periodBetween.getDays() > 7) {
            // Annulation sans frais
            billetRepository.delete(billet);
            epreuve.setNombrePlacesVente(epreuve.getNombrePlacesVente() + 1);
            sendConfirmation(billet, 100.0);
        } else if (periodBetween.getDays() >= 3) {
            // Remboursement de 50%
            billetRepository.delete(billet);
            epreuve.setNombrePlacesVente(epreuve.getNombrePlacesVente() + 1);
            sendConfirmation(billet, 50.0);
        } else {
            // Pas d'annulation possible
            throw new IllegalStateException("Annulation non possible après 3 jours avant la date de l'épreuve");
        }
    }

    private void sendConfirmation(Billet billet, double pourcentageRemboursement) {
        double montantRembourse = billet.getPrix() * (pourcentageRemboursement / 100);
        // Envoyer une confirmation par email ou tout autre moyen avec le montant remboursé
        System.out.println("Annulation confirmée pour le billet ID: " + billet.getId() + ". Montant remboursé: " + montantRembourse);
    }

    public List<Billet> recupererTousLesBilletParSpectateur(Long spectateurId){
        return billetRepository.findBySpectateurId(spectateurId);
    }

    public List<Billet> recupererTousLesBilletParEpreuve(Long spectateurId){
        return billetRepository.findByEpreuveId(spectateurId);
    }

    public List<Billet> recupererTousLesBille(){
        return billetRepository.findAll();
    }
}
