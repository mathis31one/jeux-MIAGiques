package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "infrastructure")
public class Infrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String addresse;

    @Column
    private Integer capacite;
}
