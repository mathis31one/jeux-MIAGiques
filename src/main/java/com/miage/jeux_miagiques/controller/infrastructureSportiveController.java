package com.miage.jeux_miagiques.controller;

import com.miage.jeux_miagiques.dao.model.InfrastructureSportive;
import com.miage.jeux_miagiques.service.InfrastructureSportiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infrastructures")
public class infrastructureSportiveController {
    @Autowired
    private InfrastructureSportiveService infrastructureSportiveService;

    @GetMapping
    public ResponseEntity<List<InfrastructureSportive>> getAllInfrastructureSportives() {
        List<InfrastructureSportive> infrastructures = infrastructureSportiveService.getAllInfrastructureSportives();
        return ResponseEntity.ok(infrastructures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfrastructureSportive> getInfrastructureSportiveById(@PathVariable Long id) {
        Optional<InfrastructureSportive> infrastructure = infrastructureSportiveService.getInfrastructureSportiveById(id);
        return infrastructure.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InfrastructureSportive> createInfrastructureSportive(@RequestBody InfrastructureSportive infrastructureSportive) {
        InfrastructureSportive createdInfrastructure = infrastructureSportiveService.createOrUpdateInfrastructureSportive(infrastructureSportive);
        return ResponseEntity.ok(createdInfrastructure);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfrastructureSportive> updateInfrastructureSportive(@PathVariable Long id, @RequestBody InfrastructureSportive updatedInfrastructureSportive) {
        Optional<InfrastructureSportive> existingInfrastructure = infrastructureSportiveService.getInfrastructureSportiveById(id);
        if (existingInfrastructure.isPresent()) {
            updatedInfrastructureSportive.setId(id);
            InfrastructureSportive savedInfrastructure = infrastructureSportiveService.createOrUpdateInfrastructureSportive(updatedInfrastructureSportive);
            return ResponseEntity.ok(savedInfrastructure);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfrastructureSportive(@PathVariable Long id) {
        Optional<InfrastructureSportive> infrastructure = infrastructureSportiveService.getInfrastructureSportiveById(id);
        if (infrastructure.isPresent()) {
            infrastructureSportiveService.deleteInfrastructureSportive(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
