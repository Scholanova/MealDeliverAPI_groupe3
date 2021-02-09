package org.scholanova.mealdeliverapi.domain;

import org.scholanova.mealdeliverapi.domain.Boisson;
import org.scholanova.mealdeliverapi.domain.ItemNourriture;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    ItemNourriture entree;
    @Column
    ItemNourriture plat;
    @Column
    ItemNourriture dessert;
    @Column
    Boisson boisson;
    @Column
    List<ItemNourriture> supplement;

    public ItemNourriture getEntree() {
        return entree;
    }

    public void setEntree(ItemNourriture entree) {
        this.entree = entree;
    }

    public ItemNourriture getPlat() {
        return plat;
    }

    public void setPlat(ItemNourriture plat) {
        this.plat = plat;
    }

    public ItemNourriture getDessert() {
        return dessert;
    }

    public void setDessert(ItemNourriture dessert) {
        this.dessert = dessert;
    }

    public Boisson getBoisson() {
        return boisson;
    }

    public void setBoisson(Boisson boisson) {
        this.boisson = boisson;
    }

    public List<ItemNourriture> getSupplement() {
        return supplement;
    }

    public void setSupplement(List<ItemNourriture> supplement) {
        this.supplement = supplement;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "entree=" + entree +
                ", plat=" + plat +
                ", dessert=" + dessert +
                ", boisson=" + boisson +
                ", supplement=" + supplement +
                '}';
    }
}