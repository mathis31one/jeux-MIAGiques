package com.miage.jeux_miagiques.dao.repository;

import com.miage.jeux_miagiques.dao.model.Participer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.miage.jeux_miagiques.dao.model.Billet;

import java.util.List;

public interface BilletRepository extends JpaRepository<Billet, Long> {
    List<Billet> findBilletByEpreuve_Id(Long epreuveId);

    List<Billet> findBilletBySpectateur_Id(Long spectateurId);
}
