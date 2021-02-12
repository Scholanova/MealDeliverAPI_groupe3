package org.scholanova.mealdeliverapi.application.controllers;

import org.scholanova.mealdeliverapi.domain.Element.Element;
import org.scholanova.mealdeliverapi.infrastructure.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ElementController {

    @Autowired
    ElementRepository elementRepository;

    @GetMapping("/restaurant/carte")
    public Iterable<Element> listElements () {
        return elementRepository.findAll();
    }

    @PostMapping("/restaurant/carte/add")
    public void addElement(@RequestBody Element element) {
        elementRepository.save(element);
    }

    @DeleteMapping("/restaurant/carte/delete/{id}")
    public void deleteElement(@PathVariable Long id) {
        elementRepository.deleteById(id);
    }

}
