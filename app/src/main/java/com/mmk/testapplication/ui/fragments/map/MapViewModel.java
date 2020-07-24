package com.mmk.testapplication.ui.fragments.map;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;
import com.mmk.testapplication.repository.MapRepository;

import java.util.List;

public class MapViewModel extends AndroidViewModel {

    private final MapRepository mapRepository;
    private final MutableLiveData<List<LatLng>> stationLocations=new MutableLiveData<>();



    @ViewModelInject
    public MapViewModel(@NonNull Application application, MapRepository mapRepository) {
        super(application);
        this.mapRepository = mapRepository;
    }

    public LiveData<List<LatLng>> getStationLocations(){
        List<LatLng> latLngList=mapRepository.getStationsLocations();
        stationLocations.postValue(latLngList);
        return stationLocations;
    }
}
