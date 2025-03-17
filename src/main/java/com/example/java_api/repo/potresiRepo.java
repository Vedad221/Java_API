package com.example.java_api.repo;

import com.example.java_api.model.Vreme;
import com.example.java_api.model.Potres;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;



public class potresiRepo {

     RestTemplate restTemplate = new RestTemplate();




    public String najdiZadnjiMesec(){
            String url="https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
            String response=restTemplate.getForObject(url, String.class);

           return response;


        }



}
