package com.pirog.weatherProject;

import com.pirog.weatherProject.controller.WeatherController;
import com.pirog.weatherProject.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherProjectApplicationTests {

	//@Autowired
	//WeatherService weatherService;

	@Test
	public void contextLoads() {
		//weatherService.getWeatherFromApixu("Paris");
	}

}
