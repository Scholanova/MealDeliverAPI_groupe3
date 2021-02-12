package org.scholanova.mealdeliverapi.infrastructure.Restaurant;

import org.scholanova.mealdeliverapi.domain.Commande.Commande;
import org.scholanova.mealdeliverapi.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
