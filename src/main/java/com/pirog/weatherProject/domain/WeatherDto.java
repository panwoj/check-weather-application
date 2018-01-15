package com.pirog.weatherProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WeatherDto {
    private Long id;
    private String city;
    private Double temperature;
}
