package com.pirog.weatherProject.controller;

import com.pirog.weatherProject.domain.WeatherDto;
import com.pirog.weatherProject.mapper.Mapper;
import com.pirog.weatherProject.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/weather")
public class WeatherController {
    private WeatherService weatherService;
    private Mapper mapper;

    @GetMapping(value = "getWeather/{city}")
    public WeatherDto getWeather(@PathVariable String city) {
        return mapper.mapToWeatherDto(weatherService.getAndSaveWeather(city));
    }

    @GetMapping(value = "getAllWeatherData")
    public List<WeatherDto> getAllWeatherData() {
        return mapper.mapToWeatherDtoList(weatherService.getAllWeatherData());
    }
}
