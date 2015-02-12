package com.liulishuo.llsdemo.model;

import java.io.Serializable;

/**
 * Created by twer on 2/13/15.
 */
public class WEATHER implements Serializable {
    private String city;
    private String weather;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
