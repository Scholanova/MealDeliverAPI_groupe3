package org.scholanova.mealdeliverapi.domain.Menu;

import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.scholanova.mealdeliverapi.domain.Element.TypeElement;
import org.scholanova.mealdeliverapi.domain.Menu.Exception.MenuMauvaisTypeException;

import javax.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Element entree;

    @ManyToOne
    private Element plat;

    @ManyToOne
    private Element dessert;

    @ManyToOne
    private Element boisson;

    public Long getId() {
        return id;
    }

    public Element getEntree() {
        return entree;
    }

    public Element getPlat() {
        return plat;
    }

    public Element getDessert() {
        return dessert;
    }

    public Element getBoisson() {
        return boisson;
    }

    public Element verifType(TypeElement typeAttendu, Element element) {
        if( typeAttendu != element.getType()){
            throw new MenuMauvaisTypeException(element.getNom() + " n'est pas un(e) " + typeAttendu);
        }
        return element;
    }

    public Menu(Element entree, Element plat, Element dessert, Element boisson){
            this.entree = verifType(TypeElement.ENTREE, entree);
            this.plat = verifType(TypeElement.PLAT, plat);
            this.dessert = verifType(TypeElement.DESSERT, dessert);
            this.boisson = verifType(TypeElement.BOISSON, boisson);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", entree=" + entree +
                ", plat=" + plat +
                ", dessert=" + dessert +
                ", boisson=" + boisson +
                '}';
    }
}
