package ca.uqam.projet.controllers;

import ca.uqam.projet.repositories.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

  @Autowired CitationRepository repository;
  @Autowired FoodTruckList foodTruckList;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("citations", repository.findAll());
    model.addAttribute("horaires-camions", foodTruckList.getFoodTruckList());
    return "index";
  }
}
