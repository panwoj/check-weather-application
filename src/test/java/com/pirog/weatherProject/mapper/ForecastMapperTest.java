package com.pirog.weatherProject.mapper;

import com.pirog.weatherProject.domain.Forecast;
import com.pirog.weatherProject.domain.ForecastDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ForecastMapperTest {

    @InjectMocks
    private ForecastMapper forecastMapper;

    @Test
    public void testMapToForecastDtoList() {
        //Given
        List<Forecast> forecastList = new ArrayList<>();
        Forecast forecast1 = new Forecast
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        Forecast forecast2 = new Forecast
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        Forecast forecast3 = new Forecast
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        forecastList.add(forecast1);
        forecastList.add(forecast2);
        forecastList.add(forecast3);
        //When
        List<ForecastDto> forecastDtoList = forecastMapper.mapToForecastDtoList(forecastList);
        //Then
        Assert.assertEquals(forecastList.size(), forecastDtoList.size());
    }

}
