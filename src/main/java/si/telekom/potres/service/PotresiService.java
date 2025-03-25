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
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
        String response = restTemplate.getForObject(url, String.class);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(response.split("\\n")));

        double maxMagnitude = 0;


        ObjectMapper mapper = new ObjectMapper();
        String najMocnejsi = "";
        String kraj = "";
        double globina;
        String geoLokacija = "";
        double lati = 0;
        double longi = 0;


        Potres potresTeden = null;
        for (String s : list) {
            try {
                JsonNode node = mapper.readTree(s);
                JsonNode features = node.path("properties");
                JsonNode geometry = features.path("geometry");
                double magnitude = features.path("mag").asDouble();

                if (magnitude > maxMagnitude) {
                    maxMagnitude = magnitude;
                    kraj = features.path("place").asText();
                    List<Double> coord = new ArrayList<>();
                    log.info(coord.toString());
                    coord.add(features.path("coordinates").asDouble());
                    log.info(coord.get(0).toString());
                    geoLokacija=coord.get(0) + "," + coord.get(1);
                    globina = coord.get(2);
                    potresTeden = new Potres(kraj, geoLokacija, globina);

                }


            } catch (Exception e) {
                log.error("Error parsing Json: ", e);

            }
        }

        return potresTeden;
    }







}

