package com.bit_project.typeweatherwithoutgit.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "daily_temperature")
public class DailyTemperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    private long cityId;



    private LocalTime sunrise;
    private LocalTime sunset;

    @Column(name = "min_temperature")
    private float minTemperature;
    @Column(name = "max_temperature")
    private float  maxTemperature;
    @Column(name = "evening_temperature")
    private float eveningTemperature;
    @Column(name = "night_temperature")
    private float nightTemperature;
    @Column(name = "day_temperature")
    private float dayTemperature;
    @Column(name = "morning_temperature")
    private float morningTemperature;

    private int cloudiness;

    @Column(name = "feels_like_evening")
    private float feelsLikeEvening;
    @Column(name = "feels_like_night")
    private float feelsLikeNight;
    @Column(name = "feels_like_day")
    private float feelsLikeDay;
    @Column(name = "feels_like_morning")
    private float feelsLikeMorning;

    private String sky;

    @Column(name = "wind_degree")
    private Integer windDegree;
    @Column(name = "wind_speed")
    private Float windSpeed;


    private Float pop;
    @Column(name = "weather_description")
    private String description;

    public DailyTemperature(LocalDate date, long cityId, LocalTime sunrise, LocalTime sunset, float minTemperature, float maxTemperature, float eveningTemperature, float nightTemperature, float dayTemperature, float morningTemperature, int cloudiness, float feelsLikeEvening, float feelsLikeNight, float feelsLikeDay, float feelsLikeMorning, String sky, Integer windDegree, Float windSpeed, Float pop, String description) {
        this.date = date;
        this.cityId = cityId;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.eveningTemperature = eveningTemperature;
        this.nightTemperature = nightTemperature;
        this.dayTemperature = dayTemperature;
        this.morningTemperature = morningTemperature;
        this.cloudiness = cloudiness;
        this.feelsLikeEvening = feelsLikeEvening;
        this.feelsLikeNight = feelsLikeNight;
        this.feelsLikeDay = feelsLikeDay;
        this.feelsLikeMorning = feelsLikeMorning;
        this.sky = sky;
        this.windDegree = windDegree;
        this.windSpeed = windSpeed;
        this.pop = pop;
        this.description=description;
    }

    public DailyTemperature(){}


    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getEveningTemperature() {
        return eveningTemperature;
    }

    public void setEveningTemperature(float eveningTemperature) {
        this.eveningTemperature = eveningTemperature;
    }

    public float getNightTemperature() {
        return nightTemperature;
    }

    public void setNightTemperature(float nightTemperature) {
        this.nightTemperature = nightTemperature;
    }

    public float getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(float dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public float getMorningTemperature() {
        return morningTemperature;
    }

    public void setMorningTemperature(float morningTemperature) {
        this.morningTemperature = morningTemperature;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public float getFeelsLikeEvening() {
        return feelsLikeEvening;
    }

    public void setFeelsLikeEvening(float feelsLikeEvening) {
        this.feelsLikeEvening = feelsLikeEvening;
    }

    public float getFeelsLikeNight() {
        return feelsLikeNight;
    }

    public void setFeelsLikeNight(float feelsLikeNight) {
        this.feelsLikeNight = feelsLikeNight;
    }

    public float getFeelsLikeDay() {
        return feelsLikeDay;
    }

    public void setFeelsLikeDay(float feelsLikeDay) {
        this.feelsLikeDay = feelsLikeDay;
    }

    public float getFeelsLikeMorning() {
        return feelsLikeMorning;
    }

    public void setFeelsLikeMorning(float feelsLikeMorning) {
        this.feelsLikeMorning = feelsLikeMorning;
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

    public Float getPop() {
        return pop;
    }

    public void setPop(Float pop) {
        this.pop = pop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
