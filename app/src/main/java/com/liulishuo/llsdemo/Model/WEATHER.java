package com.liulishuo.llsdemo.Model;

/**
 * Created by twer on 2/13/15
 */
public class WEATHER {
    private REALTIME realtime;
    private FORECAST forecast;

    public String getCity() {
        return forecast.city;
    }

    public void setCity(String city) {
        this.forecast.city = city;
    }

    public String getWeather() {
        return realtime.weather;
    }

    public void setWeather(String weather) {
        this.realtime.weather = weather;
    }

    public static class REALTIME {
        public String weather;
    }

    public static class FORECAST {
        public String city;
    }
}
