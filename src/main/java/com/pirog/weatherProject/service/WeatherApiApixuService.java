package com.pirog.weatherProject.service;

import com.pirog.weatherProject.apixuWeatherModel.ForecastObject;
import com.pirog.weatherProject.apixuWeatherModel.WeatherObject;
import com.pirog.weatherProject.controller.CityNotFoundException;
import com.pirog.weatherProject.domain.Forecast;
import com.pirog.weatherProject.domain.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class WeatherApiApixuService {

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();
    private final static String URL_APIXU_FORECAST =
            "http://api.apixu.com/v1/forecast.json?key=d55c5e3118664bfb9da202844181501&q=";

    @Value("${apixu.app.key}")
    private String apixuAppKey;

    @Value("${apixu.api.endpoint.prod}")
    private String apixuApiEndpoint;

    public Weather getWeatherFromApixu(String city) {
        URI URL = UriComponentsBuilder.fromHttpUrl(apixuApiEndpoint + "current.json")
                .queryParam("key", apixuAppKey)
                .queryParam("q", city).build().encode().toUri();
        ResponseEntity<WeatherObject> result = REST_TEMPLATE.getForEntity(URL, WeatherObject.class);
        if(result.getStatusCode().is4xxClientError() || result.getStatusCode().is5xxServerError()) {
            throw new CityNotFoundException();
        }
        WeatherObject weatherObject = result.getBody();
        return Weather.builder()
                .city(weatherObject.getLocation().getName())
                .country(weatherObject.getLocation().getCountry())
                .temperature(weatherObject.getCurrent().getTemp_c())
                .temperatureFeelLike(weatherObject.getCurrent().getFeelslike_c())
                .localTime(weatherObject.getLocation().getLocaltime())
                .lastUpdate(weatherObject.getCurrent().getLast_updated())
                .build();
    }

    public Forecast getForecastFromApixu(String city, int days) {
        URI URL = UriComponentsBuilder.fromHttpUrl(apixuApiEndpoint + "forecast.json")
                .queryParam("key", apixuAppKey)
                .queryParam("q", city)
                .queryParam("days", days + 1).build().encode().toUri();
        ResponseEntity<ForecastObject> result = REST_TEMPLATE.getForEntity(URL, ForecastObject.class);
        if(result.getStatusCode().is4xxClientError() || result.getStatusCode().is5xxServerError()) {
            throw new CityNotFoundException();
        }
        ForecastObject forecastObject = result.getBody();
        return Forecast.builder()
                .city(forecastObject.getLocation().getName())
                .country(forecastObject.getLocation().getCountry())
                .maxtemp_c(forecastObject.getForecast().getForecastday().
                        getForecastDayElementList().get(days).getDay().getMaxtemp_c())
                .mintemp_c(forecastObject.getForecast().getForecastday().
                        getForecastDayElementList().get(days).getDay().getMintemp_c())
                .avgtemp_c(forecastObject.getForecast().getForecastday().
                        getForecastDayElementList().get(days).getDay().getAvgtemp_c())
                .date(forecastObject.getForecast().getForecastday().getForecastDayElementList().get(days).getDate())
                .text(forecastObject.getForecast().getForecastday().
                        getForecastDayElementList().get(days).getDay().getCondition().getText())
                .build();
    }

}
