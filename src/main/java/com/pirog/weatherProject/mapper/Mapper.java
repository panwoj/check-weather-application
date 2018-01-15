package com.pirog.weatherProject.mapper;

import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.domain.WeatherDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Weather mapToWeather(final WeatherDto weatherDto) {
        return new Weather(weatherDto.getId(), weatherDto.getCity(), weatherDto.getTemperature());
    }

    public WeatherDto mapToWeatherDto(final Weather weather) {
        return new WeatherDto(weather.getId(), weather.getCity(), weather.getTemperature());
    }
}
