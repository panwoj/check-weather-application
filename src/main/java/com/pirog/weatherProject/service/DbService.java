package com.pirog.weatherProject.service;

import com.pirog.weatherProject.domain.Weather;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DbService {
    public Weather getWeather(final String city) {
        Random random = new Random();
        Double temperature = (double)random.nextInt(30);
        return new Weather((long)1, city, temperature);
    }
}
