package ca.uqam.projet;

import ca.uqam.projet.repositories.FoodTruckList;
import ca.uqam.projet.resources.FoodTruck;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableScheduling
public class Application {
   
   

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //Si besoin de le faire une fois au debut quand je roule le programme
        //GetFoodTruckList();
    }
 @Autowired JdbcTemplate jdbcTemplate;
 
    @Scheduled(cron = "*/10 * * * * *" )
    public void GetFoodTruckList() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); //Test
        RestTemplate restTemplate = new RestTemplate();
        FoodTruckList foodTruckList = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruckList.class);
        jdbcTemplate.execute("DROP TABLE foodtrucks IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE foodtrucks(" +
                "camion VARCHAR(255) NOT NULL, truckid VARCHAR(255) NOT NULL, PRIMARY KEY (truckid));"); 
        List<FoodTruck> trucksList = foodTruckList.getFoodTruckList();
        List<Object[]> foodTruckInfoList = new ArrayList< >();
        for (FoodTruck foodTruck : trucksList) {
            String foodTruckInfo[] = {foodTruck.getProperties().getTruckid(), foodTruck.getProperties().getCamion()};
            foodTruckInfoList.add(foodTruckInfo);      
        }
        jdbcTemplate.batchUpdate("INSERT INTO foodtrucks(camion, truckid) VALUES (?,?)", foodTruckInfoList);
        
        System.out.println("The time is now " + dateFormat.format(new Date())); //Test
        System.out.println(foodTruckList);
    }
}
