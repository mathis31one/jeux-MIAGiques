package com.miage.jeux_miagiques.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "billet")
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "epreuve_id", nullable = false)
    private Epreuve epreuve;

    @ManyToOne
    @JoinColumn(name = "spectateur_id", nullable = false)
    private Spectateur spectateur;

    @Column(name = "prix")
    private double prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatBillet etat;


    public enum EtatBillet {
        VALIDE,
        ANNULE,
        REMBOURSE
    }
}
