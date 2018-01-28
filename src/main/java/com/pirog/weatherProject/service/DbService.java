package com.pirog.weatherProject.service;

import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DbService {
    @Autowired
    private WeatherRepository repository;
    @Autowired
    private WeatherService weatherService;

    public List<Weather> getAllWeatherData() {
        return repository.findAll();
    }

    public Weather getAndSaveWeather(String city) {
        Weather weather = weatherService.getWeatherFromApixu(city);
        repository.save(weather);
        return weather;
    }
}
