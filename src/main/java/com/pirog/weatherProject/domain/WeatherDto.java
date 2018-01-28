package com.pirog.weatherProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class WeatherDto {
    private Long id;
    private String city;
    private String country;
    private Double temperature;
    private Double temperatureFeelLike;
    private String localTime;
    private String lastUpdate;
}
