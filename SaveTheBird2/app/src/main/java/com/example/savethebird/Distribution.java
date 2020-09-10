package com.example.savethebird;

public class Distribution {
    private String longitude;
    private String latitude;
    private String ObservationDate;

    public Distribution(String longitude, String latitude, String ObservationDate){
        this.longitude = longitude;
        this.latitude = latitude;
        this.ObservationDate = ObservationDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getObservationDate() {
        return ObservationDate;
    }
}
