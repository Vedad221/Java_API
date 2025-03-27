package si.telekom.potres.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.telekom.potres.model.Vreme;

@Slf4j
@Service
public class VremeService {
    private RestTemplate restTemplate;

    @Autowired
    public VremeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String api_key = "72b390c3c9e77e7a8590daa3ecb56003";

    @CircuitBreaker(name = "vreme", fallbackMethod = "fallbackZaVreme")
    public Vreme pridobiVreme(double lat, double lng) {
        String url = String.format("https://history.openweathermap.org/data/2.5/history/city?lat=%f&lon=%f&type=hour&units=metric&appid=%s",
                lat, lng, api_key);

        Vreme vremeZadnje = null;
        try {


            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            JsonNode data = rootNode.path("main");
            double temp = data.get("temp").asDouble();

            JsonNode weather = rootNode.path("weather");
            String stanje = weather.get("description").asText();

            vremeZadnje = new Vreme(stanje, temp);
            log.info("Vreme zadnje " + vremeZadnje);


        } catch (Exception e) {
        log.error("Vreme zadnje " + vremeZadnje);

        }


        return vremeZadnje;


    }
}

