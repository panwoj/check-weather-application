package com.pirog.weatherProject.mapper;

import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.domain.WeatherDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class WeatherMapperTest {

    @InjectMocks
    private WeatherMapper weatherMapper;

    @Test
    public void mapToWeatherDtoList() {
        //Given
        List<Weather> weathersList = new ArrayList<>();
        Weather weather1 = new Weather((long)1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        Weather weather2 = new Weather((long)1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        Weather weather3 = new Weather((long)1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        weathersList.add(weather1);
        weathersList.add(weather2);
        weathersList.add(weather3);
        //When
        List<WeatherDto> weatherDtoList = weatherMapper.mapToWeatherDtoList(weathersList);
        //Then
        Assert.assertEquals(weathersList.size(), weatherDtoList.size());
    }

    @Test
    public void mapToWeatherDto() {
        //Given
        Weather weather1 = new Weather((long)1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        //When
        WeatherDto weatherDto1 = weatherMapper.mapToWeatherDto(weather1);
        //Then
        Assert.assertEquals(weatherDto1.getCity(), weather1.getCity());
        Assert.assertEquals(weatherDto1.getId(), weather1.getId());
        Assert.assertEquals(weatherDto1.getCountry(), weather1.getCountry());
        Assert.assertEquals(weatherDto1.getTemperature(), weather1.getTemperature());
        Assert.assertEquals(weatherDto1.getTemperatureFeelLike(), weather1.getTemperatureFeelLike());
        Assert.assertEquals(weatherDto1.getLastUpdate(), weather1.getLastUpdate());
        Assert.assertEquals(weatherDto1.getLocalTime(), weather1.getLocalTime());
    }
}
