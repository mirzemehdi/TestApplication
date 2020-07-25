package com.mmk.testapplication.repository;


import com.google.android.gms.maps.model.LatLng;
import com.mmk.testapplication.model.MetroStation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MapRepository {

    @Inject
    public MapRepository() {

    }

    /*
    * There is no Metro Stations Api for location.
    * That's why it is written hard code way
    * */

    public List<MetroStation> getMetroStations(){
        List<MetroStation> metroStations=new ArrayList<>();
        metroStations.add(new MetroStation("Inşaatçılar",new LatLng(40.391427f,49.800593f)));
        metroStations.add(new MetroStation("Elmlər Akademiyası",new LatLng(40.375017f,49.813616f)));
        metroStations.add(new MetroStation("Sahil",new LatLng(40.3717038,49.8418379)));
        metroStations.add(new MetroStation("28 May",new LatLng(40.3810566,49.8468114)));
        metroStations.add(new MetroStation("Gənclik",new LatLng(40.4004909,49.8493487)));
        metroStations.add(new MetroStation("Nəriman Nərimanov",new LatLng(40.4028298,49.8684856)));
        metroStations.add(new MetroStation("Bakmil",new LatLng(40.415898,49.876851)));
        metroStations.add(new MetroStation("Ulduz",new LatLng(40.415196,49.890649)));
        metroStations.add(new MetroStation("Koroğlu",new LatLng(40.421611,49.914635)));
        metroStations.add(new MetroStation("Qara Qarayev",new LatLng(40.417776,49.929902)));
        metroStations.add(new MetroStation("Neftçilər",new LatLng(40.4101576,49.9411186)));
        metroStations.add(new MetroStation("Xalqlar Dostluğu",new LatLng(40.396881,49.950242)));
        metroStations.add(new MetroStation("Əhmədli",new LatLng(40.3852463,49.9516957)));
        metroStations.add(new MetroStation("Nizami",new LatLng(40.380325,49.829169)));
        metroStations.add(new MetroStation("20 Yanvar",new LatLng(40.404494,49.80412)));
        metroStations.add(new MetroStation("Memar Əcəmi",new LatLng(40.41059,49.811429)));
        metroStations.add(new MetroStation("Nəsimi",new LatLng(40.3963918,49.7992966)));
        metroStations.add(new MetroStation("Azadlıq prospekti",new LatLng(40.425229,49.8396341)));
        metroStations.add(new MetroStation("Dərnəgül",new LatLng(40.4253602,49.8597015)));
        metroStations.add(new MetroStation("Şah İsmayıl Xətai",new LatLng(40.383086,49.86976)));
        metroStations.add(new MetroStation("Cəfər Cabbarlı",new LatLng(40.379554,49.8470051)));
        metroStations.add(new MetroStation("Avtovağzal",new LatLng(40.42139,49.792855)));
        metroStations.add(new MetroStation("Nəsimi",new LatLng(40.3963918,49.7992966)));

        return metroStations;
    }
}
