package org.scholanova.mealdeliverapi.domain.Commande;

import org.scholanova.mealdeliverapi.domain.Client;
import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.scholanova.mealdeliverapi.domain.Restaurant.Restaurant;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Element> contenu;

    @Column
    private Date heureLivraison;

    @Column
    boolean couvertPlastique;

    @Enumerated(EnumType.STRING)
    private EtatCommande etat;


    public Commande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Element> getContenu() {
        return contenu;
    }

    public void setContenu(List<Element> contenu) {
        this.contenu = contenu;
    }

    public Date getHeureLivraison() {
        return heureLivraison;
    }

    public void setHeureLivraison(Date heureLivraison) {
        this.heureLivraison = heureLivraison;
    }

    public boolean isCouvertPlastique() {
        return couvertPlastique;
    }

    public void setCouvertPlastique(boolean couvertPlastique) {
        this.couvertPlastique = couvertPlastique;
    }

    public EtatCommande getEtat() {
        return etat;
    }

    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }

}
