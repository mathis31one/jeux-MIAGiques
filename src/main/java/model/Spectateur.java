package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("spectateur") // Value to identify Spectateur in the table
public class Spectateur extends Personne {


}