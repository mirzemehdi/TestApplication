package com.mmk.testapplication.repository;

import com.mmk.testapplication.model.ResponseWeatherTemp;
import com.mmk.testapplication.network.ApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class WeatherRepository {
    private ApiService.WeatherApiService weatherApiService;

    @Inject
    public WeatherRepository(ApiService.WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    //Returns RxJava Single
    public Single<ResponseWeatherTemp> getWeatherInfo(){
        return weatherApiService.getWeatherTemperature();
    }
}
