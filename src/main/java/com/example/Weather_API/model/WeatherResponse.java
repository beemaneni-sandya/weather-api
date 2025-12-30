package com.example.Weather_API.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Main main;
    private Weather[] weather;
    private Wind wind;
    private Clouds clouds;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] weather) { this.weather = weather; }

    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds clouds) { this.clouds = clouds; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main implements Serializable {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }

        public double getFeels_like() { return feels_like; }
        public void setFeels_like(double feels_like) { this.feels_like = feels_like; }

        public double getTemp_min() { return temp_min; }
        public void setTemp_min(double temp_min) { this.temp_min = temp_min; }

        public double getTemp_max() { return temp_max; }
        public void setTemp_max(double temp_max) { this.temp_max = temp_max; }

        public int getPressure() { return pressure; }
        public void setPressure(int pressure) { this.pressure = pressure; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather implements Serializable {
        private String main;
        private String description;

        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind implements Serializable {
        private double speed;

        public double getSpeed() { return speed; }
        public void setSpeed(double speed) { this.speed = speed; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds implements Serializable {
        private int all;

        public int getAll() { return all; }
        public void setAll(int all) { this.all = all; }
    }
}