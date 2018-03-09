package com.pirog.weatherProject.controller;

import com.pirog.weatherProject.domain.Forecast;
import com.pirog.weatherProject.domain.ForecastDto;
import com.pirog.weatherProject.domain.Weather;
import com.pirog.weatherProject.domain.WeatherDto;
import com.pirog.weatherProject.mapper.ForecastMapper;
import com.pirog.weatherProject.mapper.WeatherMapper;
import com.pirog.weatherProject.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private WeatherMapper weatherMapper;

    @MockBean
    private ForecastMapper forecastMapper;

    @Test
    public void testGetWeatherEmptyList() throws Exception {
        //Given
        Weather weather = new Weather((long) 1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        when(weatherService.getAndSaveWeather(anyString())).thenReturn(weather);
        WeatherDto weatherDto = new WeatherDto((long) 1, "test_city", "test_country",
                2.0, 1.0, "2018-03-09", "2018-03-09");
        when(weatherMapper.mapToWeatherDto(weather)).thenReturn(weatherDto);
        //When & Then
        mockMvc.perform(get("/v1/weather/getWeather/{city}", "test_city")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.city", is("test_city")))
                .andExpect(jsonPath("$.country", is("test_country")))
                .andExpect(jsonPath("$.temperature", is(2.0)))
                .andExpect(jsonPath("$.temperatureFeelLike", is(1.0)))
                .andExpect(jsonPath("$.localTime", is("2018-03-09")))
                .andExpect(jsonPath("$.lastUpdate", is("2018-03-09")));
    }

    @Test
    public void testGetForecast() throws Exception {
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
        Forecast forecast4 = new Forecast
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        Forecast forecast5 = new Forecast
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        forecastList.add(forecast1);
        forecastList.add(forecast2);
        forecastList.add(forecast3);
        forecastList.add(forecast4);
        forecastList.add(forecast5);
        when(weatherService.getForecast(anyString(), anyInt())).thenReturn(forecastList);
        List<ForecastDto> forecastDtoList = new ArrayList<>();
        ForecastDto forecastDto1 = new ForecastDto
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        ForecastDto forecastDto2 = new ForecastDto
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        ForecastDto forecastDto3 = new ForecastDto
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        ForecastDto forecastDto4 = new ForecastDto
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        ForecastDto forecastDto5 = new ForecastDto
                ("test_city", "test_country", 1.0,
                        0.0, 0.5, "2018-03-09", "sunny");
        forecastDtoList.add(forecastDto1);
        forecastDtoList.add(forecastDto2);
        forecastDtoList.add(forecastDto3);
        forecastDtoList.add(forecastDto4);
        forecastDtoList.add(forecastDto5);
        when(forecastMapper.mapToForecastDtoList(forecastList)).thenReturn(forecastDtoList);
        //When & then
        mockMvc.perform(get("/v1/weather/getForecast?city={city}&days={days}", "test_city", 5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(5)));
    }
}
