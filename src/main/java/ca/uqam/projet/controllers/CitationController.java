package ca.uqam.projet.controllers;

import java.util.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CitationController {

    @Autowired
    CitationRepository repository;
    @Autowired
    FoodTruckList foodTruckList;

    @RequestMapping("/citations")
    public List<Citation> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/horaires-camions", method = RequestMethod.GET)
    public List<FoodTruck> getFoodTruckList(String du, String au) {
        return foodTruckList.selectFoodTruckList(du , au);
    }

    @RequestMapping("/citations/{id}")
    public Citation findById(@PathVariable("id") int id) {
        return repository.findById(id);
    }
}
