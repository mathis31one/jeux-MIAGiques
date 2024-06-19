package com.miage.jeux_miagiques.service;

import com.miage.jeux_miagiques.dao.model.InfrastructureSportive;
import com.miage.jeux_miagiques.dao.repository.InfrastructureSportiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfrastructureSportiveService {

    @Autowired
    private InfrastructureSportiveRepository infrastructureSportiveRepository;

    public List<InfrastructureSportive> getAllInfrastructureSportives() {
        return infrastructureSportiveRepository.findAll();
    }

    public Optional<InfrastructureSportive> getInfrastructureSportiveById(Long id) {
        return infrastructureSportiveRepository.findById(id);
    }

    public InfrastructureSportive createOrUpdateInfrastructureSportive(InfrastructureSportive infrastructureSportive) {
        return infrastructureSportiveRepository.save(infrastructureSportive);
    }

    public void deleteInfrastructureSportive(Long id) {
        infrastructureSportiveRepository.deleteById(id);
    }
}
