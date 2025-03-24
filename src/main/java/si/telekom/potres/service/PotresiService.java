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
        double maxMagnitude = 0;
        ArrayList<String> list = new ArrayList<>(Arrays.asList(response.split("\\n")));
        int i;
        ObjectMapper mapper = new ObjectMapper();
        ArrayList filtered = new ArrayList();

        String test = null ;
        for (i=0; i<=list.size() ;i ++) {
            try {
                JsonNode node = mapper.readTree(list.get(i));
                JsonNode features = node.path("features");
                JsonNode properties = features.path("properties");
                double magnitude = properties.path("mag").asDouble();
                test = properties.path("name").asText();

//            if (magnitude > maxMagnitude) {
//                maxMagnitude = magnitude;
//                filtered.add(s);
//            }


                } catch (Exception e) {
                    log.error("Error parsing Json: ", e);

            }
        }

        return test;
    }







}

