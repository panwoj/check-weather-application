package com.pirog.weatherProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity(name = "weather_data")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "temperature_feel_like")
    private Double temperatureFeelLike;

    @Column(name = "local_time")
    private String localTime;

    @Column(name = "last_update")
    private String lastUpdate;
}
