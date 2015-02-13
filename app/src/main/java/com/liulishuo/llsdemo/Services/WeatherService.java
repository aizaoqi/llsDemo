package com.liulishuo.llsdemo.Services;

import com.liulishuo.llsdemo.Model.WEATHER;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by twer on 2/13/15.
 */
public class WeatherService {

    private static final String WEB_SERVICE_BASE_URL = "http://weatherapi.market.xiaomi.com/wtr-v2";
    private final XiaomiWeatherService mWeatherService;

    public WeatherService() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addHeader("Connection", "Keep-Alive");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WEB_SERVICE_BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(okHttpClient))
                .build();

        mWeatherService = restAdapter.create(XiaomiWeatherService.class);
    }

    private interface XiaomiWeatherService {
        @GET("/weather?imei=529e2dd3d767bdd3595eec30dd481050&device=pisces&miuiVersion=JXCCNBD20.0&modDevice=&source=miuiWeatherApp")
        Observable<WEATHER> getWeather(@Query("cityId") int cityId);
    }

    public Observable<WEATHER> getWeather(int cityId){
        return mWeatherService.getWeather(cityId);
    }
}
