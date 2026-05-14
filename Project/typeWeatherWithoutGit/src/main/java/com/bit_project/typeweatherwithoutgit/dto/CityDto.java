package com.bit_project.typeweatherwithoutgit.dto;

import com.bit_project.typeweatherwithoutgit.entity.WeatherByCity;

public class CityDto {
    private final String cityName;
    private String countryName;
    private final WeatherByCity city;


    public CityDto(String name, String countryName, WeatherByCity city) {
        this.cityName = name;
        this.city = city;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public WeatherByCity getCity() {
        return city;
    }
}
