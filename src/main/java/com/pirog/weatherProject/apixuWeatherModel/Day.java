package com.pirog.weatherProject.apixuWeatherModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Day {
    private Double maxtemp_c;
    private Double mintemp_c;
    private Double avgtemp_c;
    private Condition condition;
}
