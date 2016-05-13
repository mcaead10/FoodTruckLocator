/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruckList {

    private String type;
    private List<FoodTruck> features;

    public FoodTruckList() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FoodTruck> getFoodTruckList() {
        return features;
    }

    public void setFoodTruckList(List<FoodTruck> foodtrucklist) {
        this.features = foodtrucklist;
    }

    @Override
    public String toString() {
        return "FoodTruckList{" + "type=" + type + ", features=" + features + '}';
    }

}
