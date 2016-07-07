package ca.uqam.projet.controllers;

import ca.uqam.projet.repositories.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

  @Autowired FoodTruckList foodTruckList;
  @Autowired BixiList bixiList;


  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("horaires-camions", foodTruckList.getFoodTruckList());
    model.addAttribute("bixi", bixiList.getBixiList());
    return "index";
  }
}
