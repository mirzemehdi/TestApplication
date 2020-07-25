package com.mmk.testapplication.ui.fragments.map;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;
import com.mmk.testapplication.model.MetroStation;
import com.mmk.testapplication.repository.MapRepository;

import java.util.List;

public class MapViewModel extends AndroidViewModel {

    private final MapRepository mapRepository;
    private final MutableLiveData<List<MetroStation>> stations=new MutableLiveData<>();



    @ViewModelInject
    public MapViewModel(@NonNull Application application, MapRepository mapRepository) {
        super(application);
        this.mapRepository = mapRepository;
    }

    public LiveData<List<MetroStation>> getMetroStations(){
        List<MetroStation> metroStationList=mapRepository.getMetroStations();
        stations.postValue(metroStationList);
        return stations;
    }
}
