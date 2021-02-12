package org.scholanova.mealdeliverapi.application.controllers;

import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.scholanova.mealdeliverapi.domain.Restaurant.Exception.ProduitNonDisponibleException;
import org.scholanova.mealdeliverapi.domain.Restaurant.Exception.RestaurantNonTrouveException;
import org.scholanova.mealdeliverapi.domain.Restaurant.Restaurant;
import org.scholanova.mealdeliverapi.domain.Restaurant.RestoContient;
import org.scholanova.mealdeliverapi.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestoContientController {

    @Autowired
    ElementRepository elementRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestoContientRepository restoContientRepository;

    @GetMapping("/{id_resto}/carte")
    public List<Element> listeElement(@PathVariable Long id_resto) {
        verifSiRestoExist(id_resto);
        List<Element> listeElement = restoContientRepository.getCarteByRestaurantId(id_resto);
        return  listeElement;
    }

    @PostMapping("/{id_resto}/carte/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addElementAlaCarte(@PathVariable Long id_resto, @RequestBody Element element) {
        verifSiRestoExist(id_resto);
        elementRepository.save(element);
        Restaurant resto = restaurantRepository.findById(id_resto).get();
        restoContientRepository.save(new RestoContient(resto, element));
    }

    @DeleteMapping("/{id_resto}/carte/delete/{id_element}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void retirerElementDeLaCarte(@PathVariable Long id_resto, @PathVariable Long id_element) {
        verifSiRestoExist(id_resto);
        Long id = restoContientRepository.getIdRestoContienteByIds(id_resto, id_element);
        if(id == null){ throw new ProduitNonDisponibleException("Le restaurant ne propose déjà plus ce produit.");}
        restoContientRepository.deleteById(id);
    }

    private void verifSiRestoExist(Long id_resto) {
        if(restaurantRepository.findById(id_resto).isEmpty()){
            throw new RestaurantNonTrouveException("Le restaurant avec l'id " + id_resto + " n'existe pas.");
        }
    }

}
