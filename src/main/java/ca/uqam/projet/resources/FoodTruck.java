package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FoodTruck {
    
private String type;
private Geometry geometry;
private Properties properties;

  public FoodTruck() {
  }
    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setValue(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "FoodTruck{" + "type=" + type + ", geometry=" + geometry + ", properties=" + properties + '}';
    }



}
