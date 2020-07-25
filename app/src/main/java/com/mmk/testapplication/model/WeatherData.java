package com.mmk.testapplication.model;

import com.mmk.testapplication.utils.Constants;



public class WeatherData {

    private int temperature;
    private String description;
    private float windSpeed;
    private int humidity;
    private String country;
    private String imageUrl;

    public WeatherData() {
    }

    public WeatherData(int temperature, String description, float windSpeed, int humidity, String country,String imageUrl) {
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.country = country;
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return Constants.WEATHER_ICONS_BASE_URL+imageUrl+".png";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTemperature() {
        return String.valueOf(temperature)+"\u00B0";
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        String[] words =description.split(" ");
        StringBuilder descriptorBuilder=new StringBuilder();
        for (String word:words) {
            descriptorBuilder.append(word).append("\n");
        }
        return descriptorBuilder.toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWindSpeed() {
        return String.valueOf(windSpeed)+"m/s";
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return String.valueOf(humidity)+"%";
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", description='" + description + '\'' +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", country='" + country + '\'' +
                '}';
    }
}
