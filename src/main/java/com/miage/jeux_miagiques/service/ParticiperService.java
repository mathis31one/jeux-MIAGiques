package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.Participer;
import com.miage.jeux_miagiques.dao.repository.ParticiperRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ParticiperService {

    @Autowired
    private ParticiperRepository participerRepository;

    public Participer creerParticipation(Participer participer) {
        return participerRepository.save(participer);
    }


    public void supprimerParticipation(Long participerId) {
        participerRepository.deleteById(participerId);
    }

    public List<Participer> recupererToutesLesParticipations() {
        return participerRepository.findAll();
    }
}
