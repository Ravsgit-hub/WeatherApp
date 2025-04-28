package com.example.weatherapp.Model;

import java.io.Serializable;

public class weatherModel implements Serializable {

    String city,humidity,temperature,description,wind_speed;
            String Degree,longitude,latitude,sunrise,sunset;

    public weatherModel(String city, String humidity, String temperature, String description, String wind_speed, String degree, String longitude, String latitude, String sunrise, String sunset) {
        this.city = city;
        this.humidity = humidity;
        this.temperature = temperature;
        this.description = description;
        this.wind_speed = wind_speed;
        Degree = degree;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCity() {
        return city;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getDegree() {
        return Degree;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
