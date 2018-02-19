package com.pirog.weatherProject.mapper;

import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.domain.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherMapper {
    public Weather mapToWeather(final WeatherDto weatherDto) {
        return new Weather(weatherDto.getId(), weatherDto.getCity(), weatherDto.getCountry(),
                weatherDto.getTemperature(), weatherDto.getTemperatureFeelLike(), weatherDto.getLocalTime(),
                weatherDto.getLastUpdate());
    }

    public WeatherDto mapToWeatherDto(final Weather weather) {
        return new WeatherDto(weather.getId(), weather.getCity(), weather.getCountry(),
                weather.getTemperature(), weather.getTemperatureFeelLike(), weather.getLocalTime(),
                weather.getLastUpdate());
    }

    public List<WeatherDto> mapToWeatherDtoList (final List<Weather> weatherList) {
        return weatherList.stream()
                .map(w -> new WeatherDto(w.getId(), w.getCity(), w.getCountry(),
                        w.getTemperature(), w.getTemperatureFeelLike(), w.getLocalTime(), w.getLastUpdate()))
                .collect(Collectors.toList());
    }
}
