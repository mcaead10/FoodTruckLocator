package ca.uqam.projet.controllers;

import ca.uqam.projet.service.BDFoodTruck;
import java.util.*;

import ca.uqam.projet.resources.*;
import ca.uqam.projet.service.BDAncrageVelo;
import ca.uqam.projet.service.BDBixi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class FoodtruckController {

    @RequestMapping(value = "/horaires-camions", method = RequestMethod.GET)
    public List<FoodTruck> getFoodTruckList(String du, String au) {
        return BDFoodTruck.select(du, au);
    }

    @RequestMapping(value = "/bixi", method = RequestMethod.GET)
    public List<Bixi> getBixiList(float longitude, float latitude) {
        return BDBixi.Select(longitude, latitude);
    }
    
    @RequestMapping(value = "/ancragevelo", method = RequestMethod.GET)
    public List<AncrageVelo> getAncrageVeloList(float longitude, float latitude) {
        return BDAncrageVelo.Select(longitude, latitude);
    }
}
