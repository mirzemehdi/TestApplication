package com.mmk.testapplication.model;

import com.squareup.moshi.Json;

import java.util.List;

public class ResponseWeatherTemp {
    @Json(name = "current") private Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Current{
        @Json(name = "temp") private int temp;
        @Json(name = "humidity") private int humidity;
        @Json(name = "wind_speed") private float wind_speed;
        @Json(name = "weather") private List<Weather> weather;

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public float getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(float wind_speed) {
            this.wind_speed = wind_speed;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public static class Weather {
            @Json(name = "description") private String description;
            @Json(name = "icon") private String icon;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }



    }

}

