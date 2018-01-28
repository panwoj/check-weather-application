package com.pirog.weatherProject.controller;

import com.pirog.weatherProject.domain.WeatherDto;
import com.pirog.weatherProject.mapper.Mapper;
import com.pirog.weatherProject.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {
    @Autowired
    private DbService dbService;
    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "getWeather/{city}")
    public WeatherDto getWeather(@PathVariable String city) throws CityNotFoundException {
        return mapper.mapToWeatherDto(dbService.getAndSaveWeather(city));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllWeatherData")
    public List<WeatherDto> getAllWeatherData() {
        return mapper.mapToWeatherDtoList(dbService.getAllWeatherData());
    }
}
