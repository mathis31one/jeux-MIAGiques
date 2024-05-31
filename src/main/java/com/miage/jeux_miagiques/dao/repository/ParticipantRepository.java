package com.miage.jeux_miagiques.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miage.jeux_miagiques.dao.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
