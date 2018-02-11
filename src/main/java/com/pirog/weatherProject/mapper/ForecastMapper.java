package com.pirog.weatherProject.mapper;

import com.pirog.weatherProject.domain.Forecast;
import com.pirog.weatherProject.domain.ForecastDto;
import org.springframework.stereotype.Component;

@Component
public class ForecastMapper {
    public ForecastDto mapToForecastDto(Forecast forecast) {
        return new ForecastDto(forecast.getCity(), forecast.getCountry(), forecast.getMaxtemp_c(),
                forecast.getMintemp_c(), forecast.getAvgtemp_c(), forecast.getDate(), forecast.getText());
    }
}
