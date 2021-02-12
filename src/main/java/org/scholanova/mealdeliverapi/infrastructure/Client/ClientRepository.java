package org.scholanova.mealdeliverapi.infrastructure.Client;

import org.scholanova.mealdeliverapi.domain.Client;
import org.scholanova.mealdeliverapi.domain.Commande.Commande;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
