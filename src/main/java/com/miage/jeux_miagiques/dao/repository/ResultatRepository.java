package com.miage.jeux_miagiques.dao.repository;

import com.miage.jeux_miagiques.dao.model.Billet;
import org.springframework.data.jpa.repository.JpaRepository;

import com.miage.jeux_miagiques.dao.model.Resultat;

import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findAllByEpreuve_Id(Long epreuveId);
}
