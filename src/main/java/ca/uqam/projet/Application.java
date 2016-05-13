package ca.uqam.projet;

import ca.uqam.projet.repositories.FoodTruckList;
import ca.uqam.projet.resources.FoodTruck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);
        RestTemplate restTemplate = new RestTemplate();
        FoodTruckList foodTruckList = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruckList.class);

        System.out.println(foodTruckList);

        /*
         // SpringApplication.run(Application.class, args);
         RestTemplate restTemplate = new RestTemplate();
         //FoodTruckList foodTruckList = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruckList.class); 
         System.out.println("--------------------HEEEEEEEEEEEEEEEERE----------------------------1");
         FoodTruck[] foodtruck = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruck[].class);
         System.out.println("--------------------HEEEEEEEEEEEEEEEERE----------------------------2");
         ArrayList<FoodTruck> foodTruckList = new ArrayList<>(Arrays.asList(foodtruck));
         System.out.println("--------------------HEEEEEEEEEEEEEEEERE----------------------------3");
         System.out.println(foodTruckList);
         */
    }
}
