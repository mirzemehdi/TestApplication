package com.mmk.testapplication.model;

import com.google.android.gms.maps.model.LatLng;

public class MetroStation {
    private String name;
    private LatLng location;

    public MetroStation(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
