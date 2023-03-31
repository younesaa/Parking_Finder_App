package com.example.InstantSystem;

import java.util.List;

public class ParkingResponse {
    private List<ParkingRecord> parkings;

    //constructeur par défaut
    public ParkingResponse() {
    }

    //constructeur avec paramètres
    public ParkingResponse(List<ParkingRecord> parkings) {
        this.parkings = parkings;
    }

    //getters et setters
    public List<ParkingRecord> getParkings() {
        return parkings;
    }

    public void setParkings(List<ParkingRecord> parkings) {
        this.parkings = parkings;
    }
}