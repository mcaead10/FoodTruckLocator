package ca.uqam.projet;

import ca.uqam.projet.service.BDFoodTruck;
import ca.uqam.projet.repositories.BixiList;
import ca.uqam.projet.repositories.FoodTruckList;
import ca.uqam.projet.resources.AncrageVelo;
import ca.uqam.projet.resources.Bixi;
import ca.uqam.projet.service.BDBixi;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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

    @Scheduled(cron = "*/10 * * * * ?")
    public void getAcrageVeloList() throws IOException {
        final String URL = "http://donnees.ville.montreal.qc.ca/dataset/c4dfdeb1-cdb7-44f4-8068-247755a56cc6/resource/78dd2f91-2e68-4b8b-bb4a-44c1ab5b79b6/download/supportvelosigs.csv";
        CSVReader reader = null;
        try {
            URL stockURL = new URL(URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            reader = new CSVReader(in);
        } catch (MalformedURLException ex) {
           
        }

        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
        strat.setType(AncrageVelo.class);
        String[] columns = new String[]{"INV_ID","INV_NO","ANC_NUM","INV_CATL_NO",
                                        "CATL_MODELE","MARQ","DATE_INSPECTION","CE_NO",
                                        "ELEMENT","CATEGORIE","COULEUR","MATERIAU","CONDITION",
                                        "INTERVENTION","EMPL_X","EMPL_Y","EMPL_Z","TERRITOIRE",
                                        "STATUT","BASE","ANCRAGE","PARC","AIRE",
                                        "EMPL_ID","ORDRE_AFFICHAGE","LONG","LAT"};
        strat.setColumnMapping(columns);

        CsvToBean csv = new CsvToBean();
        List list = csv.parse(strat, reader);
        
        for (Object list1 : list) {
            System.out.println(list1);
            
        }
    }
}
