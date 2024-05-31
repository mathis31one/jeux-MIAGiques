package com.miage.jeux_miagiques.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//Rôle choix
enum Role {
    ORGANISATEUR,
    CONTROLEUR
}

@Data
@Entity
@Table(name = "organisateur")
public class Organisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse_email", unique = true)
    private String adresseEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
