package com.pirog.weatherProject.service;

import com.pirog.weatherProject.domain.Forecast;
import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WeatherService {
    private WeatherRepository repository;
    private WeatherApiApixuService weatherApiApixuService;

    public List<String> getLast3WeatherData() {
        List<Weather> weatherList = repository.findAllByIdGreaterThan(repository.count() - 3);
        return weatherList.stream()
                .map(city -> city.getCity())
                .collect(Collectors.toList());
    }

    public Weather getAndSaveWeather(String city) {
        Weather weather = weatherApiApixuService.getWeatherFromApixu(city);
        repository.save(weather);
        return weather;
    }

    public List<Weather> getAllWeatherData() {
        return repository.findAll();
    }

    public Double getAverageFor(String country) {
        List<Weather> weathers = repository.getByCountry(country);
        if (weathers != null && weathers.size() > 0) {
            Double averageTemperatureForCountry = repository.getByCountry(country).stream()
                    .map(w -> w.getTemperature())
                    .reduce(0.0, (sum, current) -> sum += current);
            return averageTemperatureForCountry / weathers.size();
        } else {
            return 0.0;
        }
    }

    public List<Forecast> getForecast(String city, int days) {
        return weatherApiApixuService.getForecastFromApixu(city, days);
    }
}
