package com.pirog.weatherProject.repository;

import com.pirog.weatherProject.domain.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WeatherRepository extends CrudRepository<Weather, Long> {
    @Override
    List<Weather> findAll();

    @Override
    Weather save(Weather weather);

    Weather getById(Long Id);

    void deleteById(Long id);



}
