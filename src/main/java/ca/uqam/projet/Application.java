package ca.uqam.projet;

import ca.uqam.projet.repositories.AncrageVeloList;
import ca.uqam.projet.service.BDFoodTruck;
import ca.uqam.projet.repositories.BixiList;
import ca.uqam.projet.repositories.FoodTruckList;
import ca.uqam.projet.resources.AncrageVelo;
import ca.uqam.projet.resources.Bixi;
import ca.uqam.projet.service.BDAncrageVelo;
import ca.uqam.projet.service.BDBixi;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
    public void getFoodTruckList() {
        final String URL = "http://camionderue.com/donneesouvertes/geojson";
        RestTemplate restTemplate = new RestTemplate();
        FoodTruckList foodTruckList = restTemplate.getForObject(URL, FoodTruckList.class);
        BDFoodTruck.insertAll(foodTruckList);
    }

    //  @Scheduled(cron = "*/10 * * * * ?") 10 seconde
    @Scheduled(cron = "0 */10 * * * ?")
    public void getBixiList() {

        final String URL = "https://montreal.bixi.com/data/bikeStations.xml";

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = documentFactory.newDocumentBuilder();
            Document document = parser.parse(new URL(URL).openStream());

            BixiList bixiList = new BixiList();
            NodeList nList = document.getElementsByTagName("station");
            for (int i = 0; i < nList.getLength(); i++) {
                bixiList.addBixi(new Bixi((Element) nList.item(i)));
            }
            BDBixi.insertAll(bixiList);

        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void getAcrageVeloList() throws IOException {
        final String URL = "http://donnees.ville.montreal.qc.ca/dataset/c4dfdeb1-cdb7-44f4-8068-247755a56cc6/resource/78dd2f91-2e68-4b8b-bb4a-44c1ab5b79b6/download/supportvelosigs.csv";
        try {
            URL stockURL = new URL(URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            CSVReader reader = new CSVReader(in);

            String[] nextLine = reader.readNext();
            AncrageVeloList ancrageVeloList = new AncrageVeloList();
            while ((nextLine = reader.readNext()) != null) {
                ancrageVeloList.addAncrageVelo(new AncrageVelo(nextLine[25], nextLine[26]));
            }
            BDAncrageVelo.insertAll(ancrageVeloList);

        } catch (MalformedURLException ex) {

        }
    }

}
