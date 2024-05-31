package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "participant")
public class Participant extends Personne {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Store role as a string
    private String delegation;
}
