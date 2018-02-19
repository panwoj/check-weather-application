package com.pirog.weatherProject.apixuWeatherModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Forecast {
    private List<Forecastday> forecastday = new ArrayList<>();
}
