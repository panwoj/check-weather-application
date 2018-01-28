package com.pirog.weatherProject.apixuWeatherModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IncomingObject {
    private Location location;
    private Current current;

}
