package si.telekom.potres.service;

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
    public String najdiZadnjiMesec() {
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
        String response = restTemplate.getForObject(url, String.class);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(response.split("\\n")));

        double maxMagnitude = 0;



        ObjectMapper mapper = new ObjectMapper();
        String najMocnejsi = "";
        String krajMocnejsi = "";
        double globina ;


        for (String s : list) {
            try {
                JsonNode node = mapper.readTree(s);
                JsonNode features = node.path("properties");
                double magnitude = features.path("mag").asDouble();

                if (magnitude > maxMagnitude) {
                    maxMagnitude = magnitude;
                    najMocnejsi = s;
                }



                } catch (Exception e) {
                    log.error("Error parsing Json: ", e);

            }
        }

        return najMocnejsi;
    }







}

