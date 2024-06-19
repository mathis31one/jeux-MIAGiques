package com.miage.jeux_miagiques.dao.model;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "infrastructure_id")
    private InfrastructureSportive infrastructureSportive;

    @Column(name = "nombre_places_vente")
    private int nombrePlacesVente;

    @Column(name = "nombre_participants_maximum")
    private int nombreParticipantsMaximum;
}
