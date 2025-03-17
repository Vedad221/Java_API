package com.example.java_api.model;

public class Vreme {
    private String stanje;
    private double temp;


    public Vreme(String stanje, double temp) {
        this.stanje = stanje;
        this.temp = temp;

    }

    public String getStanje() {
        return stanje;
    }

    public double getTemp() {
        return temp;
    }

}
