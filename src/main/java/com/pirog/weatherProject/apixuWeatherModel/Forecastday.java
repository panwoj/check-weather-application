package com.pirog.weatherProject.apixuWeatherModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Forecastday {
    private String date;
    private Day day;
}
