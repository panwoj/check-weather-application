package com.pirog.weatherProject.service;

import com.pirog.weatherProject.apixuWeatherModel.IncomingObject;
import com.pirog.weatherProject.domain.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();

    public Weather getWeatherFromApixu(String city) {
        String URL = "http://api.apixu.com/v1/current.json?key=d55c5e3118664bfb9da202844181501&q=" + city;
        IncomingObject result = REST_TEMPLATE.getForObject(URL, IncomingObject.class);
        return new Weather((long)0, result.getLocation().getName(), result.getLocation().getCountry(),
                result.getCurrent().getTemp_c(), result.getCurrent().getFeelslike_c(),
                result.getLocation().getLocaltime(), result.getCurrent().getLast_updated());
    }



}
