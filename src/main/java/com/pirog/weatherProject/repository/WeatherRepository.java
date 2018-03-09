package com.pirog.weatherProject.repository;

import com.pirog.weatherProject.domain.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WeatherRepository extends CrudRepository<Weather, Long> {

    List<Weather> findAllByIdGreaterThan(long number);

    @Override
    Weather save(Weather weather);

    List<Weather> getByCountry(String country);

    @Override
    List<Weather> findAll();






}
