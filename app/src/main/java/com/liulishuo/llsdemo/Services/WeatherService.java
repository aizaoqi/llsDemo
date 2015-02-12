package com.liulishuo.llsdemo.Services;

import com.liulishuo.llsdemo.Model.WEATHER;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;
import rx.observers.Observers;

/**
 * Created by twer on 2/13/15.
 */
public class WeatherService {

    private static final String WEB_SERVICE_BASE_URL = "http://weatherapi.market.xiaomi.com/wtr-v2";
    private final XiaomiWeatherService mWeatherService;

    public WeatherService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WEB_SERVICE_BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mWeatherService = restAdapter.create(XiaomiWeatherService.class);
    }

    private interface XiaomiWeatherService {
        @GET("/weather?cityId={cityId}&imei=529e2dd3d767bdd3595eec30dd481050&device=pisces&miuiVersion=JXCCNBD20.0&modDevice=&source=miuiWeatherApp")
        Observable<WEATHER> getWeather(int cityId);
    }

    public Observers<WEATHER> getWeather(int cityId){
        return mWeatherService.getWeather(cityId)
    }
}
