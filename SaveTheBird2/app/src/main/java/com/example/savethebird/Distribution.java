package com.example.savethebird;

public class Distribution {
    private double longitude;
    private double latitude;
    private String ObservationDate;

    public Distribution(double longitude, double latitude, String ObservationDate){
        this.longitude = longitude;
        this.latitude = latitude;
        this.ObservationDate = ObservationDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getObservationDate() {
        return ObservationDate;
    }
}
