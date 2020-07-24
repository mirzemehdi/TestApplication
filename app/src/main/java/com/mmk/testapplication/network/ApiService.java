package com.mmk.testapplication.network;

import com.mmk.testapplication.model.ResponseWeatherTemp;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

import static com.mmk.testapplication.utils.Constants.API_KEY;
import static com.mmk.testapplication.utils.Constants.BAKU_LAT;
import static com.mmk.testapplication.utils.Constants.BAKU_LNG;

public interface ApiService {
    interface WeatherApiService{

        @GET("onecall?lat="+BAKU_LAT+"&lon="+BAKU_LNG+"&appid="+API_KEY+"&units=metric")
        Single<ResponseWeatherTemp> getWeatherTemperature();
    }
}
