package com.miage.jeux_miagiques.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "epreuve")
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_epreuve")
    private String nomEpreuve;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "infrastructure_accueil")
    private String infrastructureAccueil;

    @Column(name = "nombre_places_vente")
    private int nombrePlacesVente;

}
