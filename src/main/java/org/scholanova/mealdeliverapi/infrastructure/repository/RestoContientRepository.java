package org.scholanova.mealdeliverapi.infrastructure.repository;

import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.scholanova.mealdeliverapi.domain.Restaurant.RestoContient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestoContientRepository extends CrudRepository<RestoContient, Long> {

    @Query("SELECT e FROM Element e, RestoContient c, Restaurant r " +
            "WHERE e.id = c.element.id " +
            "AND r.id = c.restaurant.id " +
            "AND c.restaurant.id = ?1 " +
            "ORDER BY type DESC")
    List<Element> getCarteByRestaurantId(Long id_resto);

    @Query("SELECT c.id FROM Element e, RestoContient c, Restaurant r " +
            "WHERE c.restaurant.id = ?1 " +
            "AND c.element.id = ?2")
    Long getIdRestoContienteByIds(Long id_resto, Long id_element);

}
