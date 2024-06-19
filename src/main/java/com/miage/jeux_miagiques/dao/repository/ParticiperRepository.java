package com.miage.jeux_miagiques.dao.repository;

import com.miage.jeux_miagiques.dao.model.Participer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticiperRepository extends JpaRepository<Participer, Long>{
    List<Participer> findByEpreuveId(Long epreuveId);
}

