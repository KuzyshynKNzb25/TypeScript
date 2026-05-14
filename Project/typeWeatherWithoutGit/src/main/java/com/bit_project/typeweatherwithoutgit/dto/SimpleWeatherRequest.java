package com.bit_project.typeweatherwithoutgit.dto;

import com.bit_project.typeweatherwithoutgit.entity.DailyTemperature;
import com.bit_project.typeweatherwithoutgit.entity.HourlyWeather;

import java.util.List;

public class SimpleWeatherRequest {
    private List<HourlyWeather>  hourlyWeather;
    private List<DailyTemperature> dailyTemperature;
    private String cityName;

    public List<DailyTemperature> getDailyTemperature() {
        return dailyTemperature;
    }

    public void setDailyTemperature(List<DailyTemperature> dailyTemperature) {
        this.dailyTemperature = dailyTemperature;
    }

    public List<HourlyWeather> getHourlyWeather() {
        return hourlyWeather;
    }

    public void setHourlyWeather(List<HourlyWeather> hourlyWeather) {
        this.hourlyWeather = hourlyWeather;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
