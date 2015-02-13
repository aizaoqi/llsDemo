package com.liulishuo.llsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liulishuo.llsdemo.Model.WEATHER;
import com.liulishuo.llsdemo.Services.WeatherService;
import com.liulishuo.llsdemo.View.AspectRatioImageView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends ActionBarActivity {

    public static final int CITY_ID = 101010100;

    @InjectView(R.id.cityWeather_tv)
    TextView cityWeather_textView;

    @InjectView(R.id.imageRoot_ll)
    LinearLayout imageRoot_layout;

    public enum TestDataEnum {
        TestData1("http://llss.qiniudn.com/forum/image/525d1960c008906923000001_1397820588.jpg"),
        TestData2("http://llss.qiniudn.com/forum/image/e8275adbeedc48fe9c13cd0efacbabdd_1397877461243.jpg"),
        TestData3("http://llss.qiniudn.com/uploads/forum/topic/attached_img/5350db2ffcfff258b500dcb2/_____2014-04-18___3.52.33.png");

        private String imageURL;

        private TestDataEnum(String url) {
            this.imageURL = url;
        }

        public String getURL() {
            return this.imageURL;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        updateWeather();
        randomLoadImages();
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

    private void randomLoadImages(){
        List<TestDataEnum> list = Arrays.asList(TestDataEnum.values());
        Collections.shuffle(list);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(TestDataEnum data : list){
            AspectRatioImageView iv = new AspectRatioImageView(getApplicationContext());
            iv.setLayoutParams(params);
            imageRoot_layout.addView(iv);
            loadImages(iv, data.getURL());
        }
    }

    private void loadImages(ImageView view, String url){
        Picasso.with(getApplicationContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(view);
    }

}
