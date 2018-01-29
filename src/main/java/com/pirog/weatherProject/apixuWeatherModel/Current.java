package com.pirog.weatherProject.apixuWeatherModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Current {
    private String last_updated;
    private Double temp_c;
    private Double feelslike_c;
}
