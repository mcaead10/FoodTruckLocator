/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruckList {

    private String type;
    private @JsonProperty("features") List<FoodTruck> features;

    public FoodTruckList() {
         features = new ArrayList<FoodTruck>();
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

    public void setFoodTruckList(List<FoodTruck> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "FoodTruckList{" + "type=" + type + ", features=" + features + '}';
    }

}
