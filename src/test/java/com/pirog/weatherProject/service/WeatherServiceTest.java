package com.pirog.weatherProject.service;

import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.repository.WeatherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherRepository repository;

    @Test
    public void testGetLast3WeatherDataWhenOnly2Available() throws Exception {
        //Given
        List<Weather> weathersList = new ArrayList<>();
        Weather weather1 = new Weather((long)1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        Weather weather2 = new Weather((long)1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        weathersList.add(weather1);
        weathersList.add(weather2);
        when(repository.findAllByIdGreaterThan(anyLong())).thenReturn(weathersList);
        when(repository.count()).thenReturn((long)2);
        //When
        List<String> stringList = weatherService.getLast3WeatherData();
        //Then
        Assert.assertEquals(stringList.size(), weathersList.size());
        Assert.assertEquals(stringList.get(0), weathersList.get(0).getCity());
        Assert.assertEquals(stringList.get(1), weathersList.get(1).getCity());
    }

    @Test
    public void testGetAverageForNoCountry() throws Exception {
        //Given
        when(repository.getByCountry(anyString())).thenReturn(null);
        //When
        Double averageTemp = weatherService.getAverageFor("test_country");
        //Given
        Assert.assertEquals(averageTemp, 0.0, 0.0);
    }
}
