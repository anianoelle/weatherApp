package com.example.weather;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;

import com.example.weather.model.WeatherResponse;
import com.example.weather.viewmodel.WeatherViewModel;
import com.example.weatherapp.R;

public class MainActivity extends ComponentActivity {
    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherViewModel = new WeatherViewModel();

        EditText cityEditText = findViewById(R.id.cityEditText);
        Button getWeatherButton = findViewById(R.id.getWeatherButton);
        TextView weatherTextView = findViewById(R.id.weatherTextView);

        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityEditText.getText().toString();
                if (!city.isEmpty()) {
                    weatherViewModel.fetchWeather(city, "a7bdec6c024aeaf0654719a0c4a106c9");
                }
            }
        });

        weatherViewModel.getWeatherData().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                if (weatherResponse != null) {
                    String weatherInfo = "City: " + weatherResponse.getName() +
                            "\nTemperature: " + weatherResponse.getMain().getTemp() + "K";
                    weatherTextView.setText(weatherInfo);
                }
            }
        });

        weatherViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                weatherTextView.setText(error);
            }
        });
    }
}