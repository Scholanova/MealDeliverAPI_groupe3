package org.scholanova.mealdeliverapi.domain.Element;

import javax.persistence.*;

@Entity
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float prix;

    @Column
    private String nom;

    @Column
    private int tempsPreparation;

    @Enumerated(EnumType.STRING)
    private TypeElement type;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public float getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public int getTempsPreparation() {
        return tempsPreparation;
    }

    public TypeElement getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ELement{" +
                "id=" + id +
                ", prix=" + prix +
                ", nom='" + nom + '\'' +
                ", tempsPreparation=" + tempsPreparation +
                ", type=" + type +
                '}';
    }
}