package org.scholanova.mealdeliverapi.application.controllers;

import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.scholanova.mealdeliverapi.domain.Menu.Exception.MenuChoixIndisponibleException;
import org.scholanova.mealdeliverapi.domain.Menu.Menu;
import org.scholanova.mealdeliverapi.domain.Menu.MenuPersistence;
import org.scholanova.mealdeliverapi.infrastructure.repository.MenuRepository;
import org.scholanova.mealdeliverapi.infrastructure.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuController {

    @Autowired
    ElementRepository elementRepository;

    @Autowired
    MenuRepository menuRepository;

    @PostMapping("/restaurant/addMenu")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenu(@RequestBody MenuPersistence menu) {

            Element entree = elementRepository.findByName(menu.getEntree());
            if(entree == null){ throw new MenuChoixIndisponibleException(menu.getEntree() + " n'est pas disponible.");}

            Element plat = elementRepository.findByName(menu.getPlat());
            if(plat == null){ throw new MenuChoixIndisponibleException(menu.getPlat() + " n'est pas disponible.");}

            Element dessert = elementRepository.findByName(menu.getDessert());
            if(dessert == null){ throw new MenuChoixIndisponibleException(menu.getDessert() + " n'est pas disponible.");}

            Element boisson = elementRepository.findByName(menu.getBoisson());
            if(boisson == null){ throw new MenuChoixIndisponibleException(menu.getBoisson() + " n'est pas disponible.");}

            Menu newMenu = new Menu(entree, plat, dessert, boisson);
            menuRepository.save(newMenu);
    }

    @GetMapping("/restaurant/menus")
    public Iterable<Menu> listeMenus() {
        return menuRepository.findAll();
    }

    @GetMapping("/restaurant/menus/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuRepository.findById(id).get();
    }

}
