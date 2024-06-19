package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Delegation;
import com.miage.jeux_miagiques.dao.repository.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DelegationService {

    @Autowired
    private DelegationRepository delegationRepository;

    public Delegation inscrireDelegation(String nomDelegation) {
        Delegation delegation= new Delegation();
        delegation.setNom(nomDelegation);
        delegation.setNombreMedaillesArgent(0);
        delegation.setNombreMedaillesBronze(0);
        delegation.setNombreMedaillesOr(0);
        return delegationRepository.save(delegation);
    }

    public void supprimerDelegation(Long delegationId) {
        delegationRepository.deleteById(delegationId);
    }

    public List<Delegation> recupererToutesLesDelegations() {
        return delegationRepository.findAll();
    }

    public List<Delegation> recupererClassementGeneral(){
        List<Delegation> delegations = delegationRepository.findAll();

        // Sort delegations based on the number of gold, silver, and bronze medals
        List<Delegation> sortedDelegations = delegations.stream()
                .sorted((d1, d2) -> {
                    // Compare l'or
                    int ComparaisonOr = Integer.compare(d2.getNombreMedaillesOr(), d1.getNombreMedaillesOr());
                    if (ComparaisonOr != 0) {
                        return ComparaisonOr;
                    }
                    // Compare l'argent
                    int ComparaisonArgent = Integer.compare(d2.getNombreMedaillesArgent(), d1.getNombreMedaillesArgent());
                    if (ComparaisonArgent != 0) {
                        return ComparaisonArgent;
                    }
                    // Compare le bronze
                    return Integer.compare(d2.getNombreMedaillesBronze(), d1.getNombreMedaillesBronze());
                })
                .collect(Collectors.toList());

        return sortedDelegations;
    }
}
