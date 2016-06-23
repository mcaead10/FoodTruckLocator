package ca.uqam.projet;

import ca.uqam.projet.service.BDFoodTruck;
import ca.uqam.projet.repositories.BixiList;
import ca.uqam.projet.repositories.FoodTruckList;
import ca.uqam.projet.resources.Bixi;
import ca.uqam.projet.service.BDBixi;
import java.io.IOException;
import java.net.URL;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

    //  @Scheduled(cron = "*/10 * * * * ?") 10 seconde
    @Scheduled(cron = "0 */10 * * * ?")
    public void GetBixiList() {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = documentFactory.newDocumentBuilder();
            Document document = parser.parse(new URL("https://montreal.bixi.com/data/bikeStations.xml").openStream());

            BixiList bixiList = new BixiList();
            NodeList nList = document.getElementsByTagName("station");
            for (int i = 0; i < nList.getLength(); i++) {
                 bixiList.addBixi(new Bixi((Element)nList.item(i)));
            }
            BDBixi.insertAll(bixiList);

        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
