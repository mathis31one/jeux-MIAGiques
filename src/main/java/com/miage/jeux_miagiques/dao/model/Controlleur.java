package com.miage.jeux_miagiques.dao.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "controlleur")
public class Controlleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spectateur")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse_email", unique = true)
    private String adresseEmail;
}
