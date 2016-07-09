package ca.uqam.projet.controllers;

import ca.uqam.projet.service.BDFoodTruck;
import java.util.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.resources.*;
import ca.uqam.projet.service.BDBixi;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class FoodtruckController {

    @Autowired
    FoodTruckList foodTruckList;
    @Autowired
    BixiList bixiList;

    @RequestMapping(value = "/horaires-camions", method = RequestMethod.GET)
    public List<FoodTruck> getFoodTruckList(String du, String au) {
        return BDFoodTruck.select(du, au);
    }

    @RequestMapping(value = "/bixi", method = RequestMethod.GET)
    public List<Bixi> getBixiList(float longitude, float latitude) {
        System.out.println("latitude: " + latitude);
        System.out.println("longitude: " + longitude);
        return BDBixi.Select(longitude, latitude);
    }
}
