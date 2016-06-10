package ca.uqam.projet.controllers;

import java.util.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CitationController {

  @Autowired CitationRepository repository;
  @Autowired FoodTruckList foodTruckList;

  @RequestMapping("/citations")
  public List<Citation> findAll() {
    return repository.findAll();
  }
  
  @RequestMapping("/horaires-camions")
  public List<FoodTruck> getFoodTruckList(){
    //  System.out.println(foodTruckList.getFoodTruckList());
    return foodTruckList.selectFoodTruckList();
  }

  @RequestMapping("/citations/{id}")
  public Citation findById(@PathVariable("id") int id) {
    return repository.findById(id);
  }
}
