package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Delegation;
import com.miage.jeux_miagiques.dao.repository.DelegationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
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
}
