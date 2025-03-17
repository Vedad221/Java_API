package com.example.java_api.repo;




import org.springframework.web.client.RestTemplate;


public class PotresiRepo {

     private final RestTemplate restTemplate = new RestTemplate();




    public  String najdiZadnjiMesec(){
            String url="https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
            String response=restTemplate.getForObject(url, String.class);



           return response;



        }



}
