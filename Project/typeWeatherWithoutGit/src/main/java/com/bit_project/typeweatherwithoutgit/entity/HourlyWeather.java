package com.bit_project.typeweatherwithoutgit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hourly_weather")
public class HourlyWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float  temperature;
    private LocalDateTime time;

    @Column(name = "cloudness")
    private Integer cloudiness;

    @Column(name = "feels_like")
    private Float feelsLike;
    private String sky;

    @Column(name = "wind_degree")
    private Integer windDegree;

    @Column(name = "wind_speed")
    private Float windSpeed;

    @Column(name = "city_id")
    private long cityId;

    private float pop;

    @Column(name = "weather_description")
    private String description;

    public  HourlyWeather() {}

    public HourlyWeather(Float temperature, LocalDateTime time, Integer cloudiness, Float feelsLike, String sky, Integer windDegree, Float windSpeed, long cityId, float pop, String description) {
        this.temperature = temperature;
        this.time = time;
        this.cloudiness = cloudiness;
        this.feelsLike = feelsLike;
        this.sky = sky;
        this.windDegree = windDegree;
        this.windSpeed = windSpeed;
        this.cityId = cityId;
        this.pop = pop;
        this.description = description;
    }


    public Float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Integer getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPop() {
        return pop;
    }

    public void setPop(float pop) {
        this.pop = pop;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
