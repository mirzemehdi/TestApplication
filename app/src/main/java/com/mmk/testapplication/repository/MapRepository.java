package com.mmk.testapplication.repository;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MapRepository {

    @Inject
    public MapRepository() {

    }

    public List<LatLng> getStationsLocations(){
        List<LatLng> metroStations=new ArrayList<>();
        metroStations.add(new LatLng(40.391427f,49.800593f));
        metroStations.add(new LatLng(40.375017f,49.813616f));

        return metroStations;
    }
}
