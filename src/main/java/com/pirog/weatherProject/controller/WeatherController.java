package com.pirog.weatherProject.controller;

import com.pirog.weatherProject.domain.ForecastDto;
import com.pirog.weatherProject.domain.WeatherDto;
import com.pirog.weatherProject.mapper.ForecastMapper;
import com.pirog.weatherProject.mapper.WeatherMapper;
import com.pirog.weatherProject.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/weather")
public class WeatherController {
    private WeatherService weatherService;
    private WeatherMapper weatherMapper;
    private ForecastMapper forecastMapper;

    @GetMapping(value = "getWeather/{city}")
    public WeatherDto getWeather(@PathVariable String city) {
        return weatherMapper.mapToWeatherDto(weatherService.getAndSaveWeather(city));
    }

    @GetMapping(value = "getAllWeatherData")
    public List<WeatherDto> getAllWeatherData() {
        return weatherMapper.mapToWeatherDtoList(weatherService.getAllWeatherData());
    }

    @GetMapping(value = "getAverageTempForCountry/{country}")
    public Double getAverageTemperature(@PathVariable String country) {
        return weatherService.getAverageFor(country);
    }

    @GetMapping(value = "getForecast")
    public ForecastDto getForecast(
            @RequestParam(value="city", required = true) String city,
            @RequestParam(value="days", defaultValue = "1") int days) {
        return forecastMapper.mapToForecastDto(weatherService.getForecast(city, days));
    }
}
