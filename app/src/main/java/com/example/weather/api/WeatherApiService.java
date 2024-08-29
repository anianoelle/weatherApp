package com.example.weather.api;
import com.example.weather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String apiKey);
}
