package org.scholanova.mealdeliverapi.domain;

import org.scholanova.mealdeliverapi.domain.Commande.EtatCommande;

public class ItemNourriture {

    float prix;
    String nom;
    int tempsPreparation;

    public ItemNourriture() {
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(int tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    @Override
    public String   toString() {
        return "ItemNourriture{" +
                "prix=" + prix +
                ", nom='" + nom + '\'' +
                ", tempsDeCuisson=" + tempsPreparation +
                '}';
    }
}
