package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String name;

    @Column(name = "prenom", nullable = false)
    private String surname;

    @Column(name = "adresse_mail", nullable = false)
    private String email;
}
