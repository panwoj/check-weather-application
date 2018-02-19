package com.pirog.weatherProject.service;

import com.pirog.weatherProject.domain.Forecast;
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

    public Double getAverageFor(String country) {
        List<Weather> weathers = repository.getByCountry(country);
        int weathersSize = weathers.size();
        Double averageTemperatureForCountry =  weathers.stream()
                .map(w -> w.getTemperature())
                //.mapToDouble().average()
                .reduce(0.0, (sum, current) -> sum += current);
        return averageTemperatureForCountry/weathersSize;
    }

    public Forecast getForecast(String city, int days) {
        return weatherApiApixuService.getForecastFromApixu(city, days);
    }
}
