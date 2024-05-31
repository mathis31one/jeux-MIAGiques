package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organisateur")
public class Organisateur extends Personne {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Store role as a string
    private Role role;

    public enum Role {
        ORGANISATEUR,
        CONTROLEUR
    }
}
