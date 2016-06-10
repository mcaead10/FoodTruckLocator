package ca.uqam.projet;

import ca.uqam.projet.repositories.BDFoodTruck;
import ca.uqam.projet.repositories.FoodTruckList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

  //  @Scheduled(cron = "*/10 * * * * ?") 10 seconde
    @Scheduled(cron = "0 0 0,12 * * ?")
    public void GetFoodTruckList() {
        RestTemplate restTemplate = new RestTemplate();
        FoodTruckList foodTruckList = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruckList.class);
        BDFoodTruck.insertAll(foodTruckList);
    }
}
