package com.pirog.weatherProject.service;

import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeatherService {
    private WeatherRepository repository;
    private WeatherApiApixuService weatherApiApixuService;

    public List<Weather> getAllWeatherData() {
        return repository.findAll();
    }

    public Weather getAndSaveWeather(String city) {
        Weather weather = weatherApiApixuService.getWeatherFromApixu(city);
        repository.save(weather);
        return weather;
    }
}
