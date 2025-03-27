package si.telekom.potres.service;

import si.telekom.potres.model.Potres;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.telekom.potres.model.Vreme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PotresiService {
    private RestTemplate restTemplate;

    @Autowired
    public PotresiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "potresi", fallbackMethod = "fallbackZaPotrese")
    public Potres najdiZadnjiMesec() {
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

        try {

            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);


            JsonNode features = rootNode.path("features");

            double maxMagnitude = 0;
            Potres najhujsiPotres = null;


            for (JsonNode feature : features) {
                JsonNode properties = feature.path("properties");
                double magnitude = properties.path("mag").asDouble();

                if (magnitude > maxMagnitude) {
                    maxMagnitude = magnitude;


                    String kraj = properties.path("place").asText();


                    JsonNode geometry = feature.path("geometry");
                    JsonNode coordinates = geometry.path("coordinates");


                    double longitude = coordinates.get(0).asDouble();
                    double latitude = coordinates.get(1).asDouble();
                    double globina = coordinates.get(2).asDouble();

                    String geoLokacija = latitude + "," + longitude;

                    najhujsiPotres = new Potres(null,kraj, geoLokacija, globina);
                }
            }

            return najhujsiPotres;
        } catch (Exception e) {
            log.error("Napaka pri pridobitvi podatkov", e);
            return new Potres(null,"N/A", "N/A", 0.0);
        }
    }
    @CircuitBreaker(name = "potresi", fallbackMethod = "fallbackZaPotrese")
    public Potres najdiZadnjiTeden() {
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";

        try {

            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);


            JsonNode features = rootNode.path("features");

            double maxMagnitude = 0;
            Potres najhujsiPotres = null;


            for (JsonNode feature : features) {
                JsonNode properties = feature.path("properties");
                double magnitude = properties.path("mag").asDouble();

                if (magnitude > maxMagnitude) {
                    maxMagnitude = magnitude;


                    String kraj = properties.path("place").asText();


                    JsonNode geometry = feature.path("geometry");
                    JsonNode coordinates = geometry.path("coordinates");


                    double longitude = coordinates.get(0).asDouble();
                    double latitude = coordinates.get(1).asDouble();
                    double globina = coordinates.get(2).asDouble();

                    String geoLokacija = latitude + "," + longitude;

                    najhujsiPotres = new Potres(null,kraj, geoLokacija, globina);
                }
            }

            return najhujsiPotres;
        } catch (Exception e) {
            log.error("Napaka pri pridobitvi podatkov", e);
            return new Potres(null,"N/A", "N/A", 0.0);
        }
    }

    @Autowired
    private VremeService vremeService;

    public Potres najdiZadnji() {

        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";

        Potres zadnjiPotres = null;
        Vreme vreme = null;
        try {

            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode feature = rootNode.path("features");


            //prvi v seznamu
             if (feature.size() > 0) {
                JsonNode properties = feature.path("properties");

                String kraj = properties.path("place").asText();

                JsonNode geometry = feature.path("geometry");
                JsonNode coordinates = geometry.path("coordinates");


                double longitude = coordinates.get(0).asDouble();
                double latitude = coordinates.get(1).asDouble();
                double globina = coordinates.get(2).asDouble();

                String geoLokacija = latitude + "," + longitude;


                vreme = vremeService.pridobiVreme(latitude, longitude);
                zadnjiPotres = new Potres(null,kraj, geoLokacija, globina);
                zadnjiPotres.setVreme(vreme);


             }


        } catch (Exception e) {
            log.error("Napaka pri pridobitvi podatkov za zadnji", e);
            return new Potres(null,"N/A", "N/A", 0.0);

        }


        return zadnjiPotres;
    }
}







