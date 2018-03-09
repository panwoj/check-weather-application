package com.pirog.weatherProject.mapper;

import com.pirog.weatherProject.domain.Forecast;
import com.pirog.weatherProject.domain.ForecastDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ForecastMapper {
    public ForecastDto mapToForecastDto(final Forecast forecast) {
        return new ForecastDto(forecast.getCity(), forecast.getCountry(), forecast.getMaxtemp_c(),
                forecast.getMintemp_c(), forecast.getAvgtemp_c(), forecast.getDate(), forecast.getText());
    }

    public List<ForecastDto> mapToForecastDtoList(final List<Forecast> forecastList) {
        return forecastList.stream()
                .map(f -> new ForecastDto(f.getCity(), f.getCountry(), f.getMaxtemp_c(), f.getMintemp_c(),
                        f.getAvgtemp_c(), f.getDate(), f.getText()))
                .collect(Collectors.toList());
    }
}
