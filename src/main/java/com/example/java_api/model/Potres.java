package com.example.java_api.model;

public class Potres {
    private String najbliziKraj;
    private String geoLokacija;
    private double globina;

    public Potres(String najbliziKraj, String geoLokacija, double globina) {
        this.najbliziKraj = najbliziKraj;
        this.geoLokacija = geoLokacija;
        this.globina = globina;

    }
    //Getteri
    public String getNajbliziKraj() {
        return najbliziKraj;
    }

    public String getGeoLokacija() {
        return geoLokacija;

    }

    public double getGlobina() {
        return globina;
    }


}
