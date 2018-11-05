package com.learningandroid.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import com.survivingwithandroid.weather.lib.WeatherClient;
//import com.survivingwithandroid.weather.lib.WeatherConfig;
//import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
//import com.survivingwithandroid.weather.lib.exception.WeatherProviderInstantiationException;
//import com.survivingwithandroid.weather.lib.model.CurrentWeather;
//import com.survivingwithandroid.weather.lib.provider.forecastio.ForecastIOProviderType;
//import com.survivingwithandroid.weather.lib.request.WeatherRequest;

public class MainActivity extends AppCompatActivity {

    TextView helloWorld, textView ;
    Button send_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloWorld = (TextView)findViewById(R.id.helloWorld);

        helloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, testActivity.class);
                startActivity(intent);
            }
        });

        textView = (TextView)findViewById(R.id.textView2);
        final OkHttpClient client = new OkHttpClient();
        final ExecutorService service = Executors.newSingleThreadExecutor();
        send_request = (Button)findViewById(R.id.send_request);
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.submit(new Runnable() {
                    @Override
                    public void run()
                    {
                        HttpUrl.Builder builder = HttpUrl.parse("https://api.openweathermap.org/data/2.5/weather?").newBuilder();
                        builder.addQueryParameter("id","7280289");
                        builder.addQueryParameter("appid","79f62d59a4a9a5ae261d42cbb068b2a4");
                        Request request = new Request.Builder()
                                .url(builder.toString())
                                .build();

//                        Request request = new Request.Builder()
//                                .url("https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1")
//                                .build();
                        try {
                            final Response response = client.newCall(request).execute();
                            final String resStr = response.body().string();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    Gson gson = new Gson();
                                    Weather currentWeather = gson.fromJson(resStr, Weather.class);
                                    String test = "coord : " + currentWeather.coord.lon +"\n" +
                                            "weather : " + currentWeather.weather[0].id +"\n" +
                                            "base : " + currentWeather.base+"\n" +
                                            "main : " + currentWeather.main.temp +"\n" +
                                            "visibility : " + currentWeather.visibility +"\n" +
                                            "wind : " + currentWeather.wind.deg +"\n" +
                                            "clouds : " + currentWeather.clouds.all +"\n" +
                                            "dt : " + currentWeather.dt +"\n" +
                                            "sys : " + currentWeather.sys.country +"\n" +
                                            "id : " + currentWeather.id +"\n" +
                                            "name : " + currentWeather.name +"\n" +
                                            "cod : " + currentWeather.cod  ;

                                    textView.setText(test);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
}
