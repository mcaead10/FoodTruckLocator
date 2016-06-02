/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.*;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class FoodTruckList {

    private @JsonProperty("features")
    List<FoodTruck> foodTruckList;

    public FoodTruckList() {
        foodTruckList = new ArrayList<>();
    }

    public List<FoodTruck> getFoodTruckList() {
        return foodTruckList;
    }
    
     public List<FoodTruck> selectFoodTruckList() {
        BDFoodTruck bDFoodTruck = new BDFoodTruck();
        return new BDFoodTruck().select();
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        this.foodTruckList.add(foodTruck);
    }

    @Override
    public String toString() {
        return "FoodTruckList{" + " foodTruckList=" + foodTruckList + '}';
    }

}
