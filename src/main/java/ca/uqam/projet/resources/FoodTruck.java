package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FoodTruck {
    
private Geometry geometry;
private Properties properties;

  public FoodTruck() {
  }
  
  public FoodTruck(String truckid, String camion, String lieu, float x, float y, Date heureDebut, Date heureFin  ) {
      geometry = new Geometry(x, y);
      properties = new Properties(lieu, camion, truckid, heureDebut, heureFin);
  }


    public Properties getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return "FoodTruck{" + "geometry=" + geometry + ", properties=" + properties + '}';
    }



}
