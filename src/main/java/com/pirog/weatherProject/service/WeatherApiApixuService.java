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

    @Value("${apixu.app.key}")
    private String apixuAppKey;

    @Value("${apixu.api.endpoint.prod}")
    private String apixuApiEndpoint;

    public Weather getWeatherFromApixu(String city) {
        URI URL = createUrlGetWeather(city);
        ResponseEntity<WeatherObject> result = REST_TEMPLATE.getForEntity(URL, WeatherObject.class);
        //checkIfErrorStatusCode(result);
        WeatherObject weatherObject = result.getBody();
        return buildWeather(weatherObject);
    }

    public Forecast getForecastFromApixu(String city, int days) {
        URI URL = createUrlGetForecast(city, days);
        ResponseEntity<ForecastObject> result = REST_TEMPLATE.getForEntity(URL, ForecastObject.class);
        checkIfErrorStatusCode(result);
        ForecastObject forecastObject = result.getBody();
        return buildForecast(forecastObject, days);
    }

    private URI createUrlGetWeather(String city) {
        return UriComponentsBuilder.fromHttpUrl(apixuApiEndpoint + "current.json")
                .queryParam("key", apixuAppKey)
                .queryParam("q", city).build().encode().toUri();
    }

    private URI createUrlGetForecast(String city, int days) {
        return UriComponentsBuilder.fromHttpUrl(apixuApiEndpoint + "forecast.json")
                .queryParam("key", apixuAppKey)
                .queryParam("q", city)
                .queryParam("days", days + 1).build().encode().toUri();
    }

    private void checkIfErrorStatusCode(ResponseEntity<ForecastObject> result) {
        if(result.getStatusCode().is4xxClientError() || result.getStatusCode().is5xxServerError()) {
            throw new CityNotFoundException();
        }
    }

    private Weather buildWeather(WeatherObject weatherObject) {
        return Weather.builder()
                .city(weatherObject.getLocation().getName())
                .country(weatherObject.getLocation().getCountry())
                .temperature(weatherObject.getCurrent().getTemp_c())
                .temperatureFeelLike(weatherObject.getCurrent().getFeelslike_c())
                .localTime(weatherObject.getLocation().getLocaltime())
                .lastUpdate(weatherObject.getCurrent().getLast_updated())
                .build();
    }

    private Forecast buildForecast(ForecastObject forecastObject, int days) {
        return Forecast.builder()
                .city(forecastObject.getLocation().getName())
                .country(forecastObject.getLocation().getCountry())
                .maxtemp_c(forecastObject.getForecast().getForecastday().get(days).getDay().getMaxtemp_c())
                .mintemp_c(forecastObject.getForecast().getForecastday().
                        get(days).getDay().getMintemp_c())
                .avgtemp_c(forecastObject.getForecast().getForecastday().
                        get(days).getDay().getAvgtemp_c())
                .date(forecastObject.getForecast().getForecastday().get(days).getDate())
                .text(forecastObject.getForecast().getForecastday().
                        get(days).getDay().getCondition().getText())
                .build();
    }
}
