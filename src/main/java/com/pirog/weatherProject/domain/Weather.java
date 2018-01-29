package com.pirog.weatherProject.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity(name = "weather_data")
@Builder
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
