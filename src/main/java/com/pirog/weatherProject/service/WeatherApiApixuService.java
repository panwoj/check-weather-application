package com.pirog.weatherProject.service;

import com.pirog.weatherProject.apixuWeatherModel.IncomingObject;
import com.pirog.weatherProject.controller.CityNotFoundException;
import com.pirog.weatherProject.domain.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiApixuService {

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();
    private final static String URL_APIXU =
            "http://api.apixu.com/v1/current.json?key=d55c5e3118664bfb9da202844181501&q=";

    public Weather getWeatherFromApixu(String city) {
        String URL = URL_APIXU + city;
        ResponseEntity<IncomingObject> result = REST_TEMPLATE.getForEntity(URL, IncomingObject.class);
        if(result.getStatusCode().is4xxClientError() || result.getStatusCode().is5xxServerError()) {
            throw new CityNotFoundException();
        }
        IncomingObject incomingObject = result.getBody();
        return Weather.builder()
                .city(incomingObject.getLocation().getName())
                .country(incomingObject.getLocation().getCountry())
                .temperature(incomingObject.getCurrent().getTemp_c())
                .temperatureFeelLike(incomingObject.getCurrent().getFeelslike_c())
                .localTime(incomingObject.getLocation().getLocaltime())
                .lastUpdate(incomingObject.getCurrent().getLast_updated())
                .build();
    }



}
