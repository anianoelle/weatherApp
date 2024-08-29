package com.example.weather.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.model.WeatherResponse;
import com.example.weather.network.WeatherApi;
import com.example.weather.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    private final MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<WeatherResponse> getWeatherData() {
        return weatherData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchWeather(String city, String apiKey) {
        WeatherApi weatherApi = RetrofitInstance.getRetrofitInstance().create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeather(city, apiKey);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    weatherData.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                errorMessage.setValue("Failure: " + t.getMessage());
            }
        });
    }
}
