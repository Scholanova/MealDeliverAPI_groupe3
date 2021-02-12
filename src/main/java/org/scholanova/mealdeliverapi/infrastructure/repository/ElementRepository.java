package org.scholanova.mealdeliverapi.infrastructure.repository;

import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Long> {

    @Query("SELECT e FROM Element e WHERE e.nom = ?1")
    Element findByName(String nom);
}
