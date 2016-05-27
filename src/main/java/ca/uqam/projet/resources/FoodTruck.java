package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FoodTruck {
    
private Geometry geometry;
private Properties properties;

  public FoodTruck() {
  }
  
  public FoodTruck(String truckid, String camion) {
      geometry = null;
      properties = new Properties(truckid, camion);
  }


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setValue(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "FoodTruck{" + "geometry=" + geometry + ", properties=" + properties + '}';
    }



}
