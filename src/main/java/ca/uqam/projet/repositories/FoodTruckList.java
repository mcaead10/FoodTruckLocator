package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class FoodTruckList {

    private final @JsonProperty("features")
    List<FoodTruck> foodTruckList;

    public FoodTruckList() {
        foodTruckList = new ArrayList<>();
    }

    public List<FoodTruck> getFoodTruckList() {
        return foodTruckList;
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        this.foodTruckList.add(foodTruck);
    }

    @Override
    public String toString() {
        return "FoodTruckList{" + " foodTruckList=" + foodTruckList + '}';
    }

}
