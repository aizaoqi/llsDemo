package com.liulishuo.llsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.liulishuo.llsdemo.Model.WEATHER;
import com.liulishuo.llsdemo.Services.WeatherService;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends ActionBarActivity {

    private static final int CITY_ID = 101010100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateWeather();
    }

    private void updateWeather() {
        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(CITY_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WEATHER>() {
                    @Override
                    public void call(WEATHER weather) {
                        Log.e("", "city:" + weather.getCity());
                    }
                });
    }
}
