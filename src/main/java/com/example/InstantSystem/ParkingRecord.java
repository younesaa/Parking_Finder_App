package com.example.InstantSystem;

public class ParkingRecord {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private int availableSpaces;

    //constructeur par défaut
    public ParkingRecord() {
    }

    //constructeur avec paramètres
    public ParkingRecord(String name, String address, double latitude, double longitude, int availableSpaces) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableSpaces = availableSpaces;
    }

    //getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }
}
