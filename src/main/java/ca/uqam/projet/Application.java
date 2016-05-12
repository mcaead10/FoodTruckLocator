package ca.uqam.projet;

import ca.uqam.projet.resources.FoodTruck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    RestTemplate restTemplate = new RestTemplate();
    FoodTruck foodtruck = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruck.class); 
    
  }
}
