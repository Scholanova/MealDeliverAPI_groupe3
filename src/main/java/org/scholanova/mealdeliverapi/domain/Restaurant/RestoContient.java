package org.scholanova.mealdeliverapi.domain.Restaurant;

import org.scholanova.mealdeliverapi.domain.Element.Element;

import javax.persistence.*;

@Entity
public class RestoContient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Restaurant restaurant;

    @ManyToOne
    Element element;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Element getElement() {
        return element;
    }

    public RestoContient(Restaurant restaurant, Element element) {
        this.restaurant = restaurant;
        this.element = element;
    }

    public RestoContient() {
    }
}
