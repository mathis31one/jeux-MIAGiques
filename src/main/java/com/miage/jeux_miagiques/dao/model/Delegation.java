package com.miage.jeux_miagiques.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "delegation")
public class Delegation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "nb_medailles_or")
    private int nombreMedaillesOr;

    @Column(name = "nb_medailles_argent")
    private int nombreMedaillesArgent;

    @Column(name = "nb_medailles_bronze")
    private int nombreMedaillesBronze;
  }