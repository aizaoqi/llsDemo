package com.liulishuo.llsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.liulishuo.llsdemo.Model.WEATHER;
import com.liulishuo.llsdemo.Services.WeatherService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends ActionBarActivity {

    public static final int CITY_ID = 101010100;

    @InjectView(R.id.cityWeather_tv)
    TextView cityWeather_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        updateWeather();
    }

    private void updateWeather() {
        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(CITY_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WEATHER>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WEATHER weather) {
                        cityWeather_textView.setText(weather.getCity()+":"+ weather.getWeather());
                    }
                });
    }
}
