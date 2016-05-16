package ca.uqam.projet;

import ca.uqam.projet.repositories.FoodTruckList;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 static @Autowired JdbcTemplate jdbcTemplate;
 
    @Scheduled(cron = "0 0 0,12 * * *" )
    public static void GetFoodTruckList() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); //Test
        RestTemplate restTemplate = new RestTemplate();
        FoodTruckList foodTruckList = restTemplate.getForObject("http://camionderue.com/donneesouvertes/geojson", FoodTruckList.class);
      //  jdbcTemplate.execute("DROP TABLE foodtruck IF EXISTS");
       // jdbcTemplate.execute("CREATE TABLE foodtruck(" +
           //     "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
        System.out.println("The time is now " + dateFormat.format(new Date())); //Test
        System.out.println(foodTruckList);
    }
}
