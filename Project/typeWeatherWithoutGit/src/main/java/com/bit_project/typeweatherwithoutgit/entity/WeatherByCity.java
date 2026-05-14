package com.bit_project.typeweatherwithoutgit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weather_by_city")
public class WeatherByCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private float lat;
    private float lon;

    @Column(name = "latest-day")
    private String latestDay;

    @Column(name = "ukrainian_name")
    private String ukrainianName;
    private String state;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "number_of_requests")
    private int numberOfRequests;

    public WeatherByCity(String name, float lat, float lon, String latestDay, String ukrainianName, String state, String countryCode) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.latestDay = latestDay;
        this.ukrainianName = ukrainianName;
        this.state = state;
        this.countryCode = countryCode;
        this.numberOfRequests = 0;
    }

    public WeatherByCity() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getLatestDay() {
        return latestDay;
    }

    public void setLatestDay(String latestDay) {
        this.latestDay = latestDay;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    public void setUkrainianName(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }
}
