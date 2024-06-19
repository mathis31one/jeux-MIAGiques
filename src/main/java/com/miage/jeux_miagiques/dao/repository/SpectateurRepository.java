package com.miage.jeux_miagiques.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miage.jeux_miagiques.dao.model.Spectateur;

public interface SpectateurRepository extends JpaRepository<Spectateur, Long> {
	
}
