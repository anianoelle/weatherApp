package com.example.weather.repository;
import com.example.weather.api.WeatherApiService;
import com.example.weather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private final WeatherApiService weatherApiService;

    public WeatherRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApiService = retrofit.create(WeatherApiService.class);
    }

    public Call<WeatherResponse> getWeather(String city, String apiKey) {
        return weatherApiService.getWeather(city, apiKey);
    }
}