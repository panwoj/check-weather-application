package com.pirog.weatherProject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class ForecastDto {
    private String city;
    private String country;
    private Double maxtemp_c;
    private Double mintemp_c;
    private Double avgtemp_c;
    private String date;
    private String text;
}
