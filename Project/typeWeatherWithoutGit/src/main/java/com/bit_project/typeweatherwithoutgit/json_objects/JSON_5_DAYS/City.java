package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City{

	@JsonProperty("country")
	private String country;

	@JsonProperty("coord")
	private Coord coord;

	@JsonProperty("sunrise")
	private Integer sunrise;

	@JsonProperty("timezone")
	private Integer timezone;

	@JsonProperty("sunset")
	private Integer sunset;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("population")
	private Integer population;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCoord(Coord coord){
		this.coord = coord;
	}

	public Coord getCoord(){
		return coord;
	}

	public void setSunrise(Integer sunrise){
		this.sunrise = sunrise;
	}

	public Integer getSunrise(){
		return sunrise;
	}

	public void setTimezone(Integer timezone){
		this.timezone = timezone;
	}

	public Integer getTimezone(){
		return timezone;
	}

	public void setSunset(Integer sunset){
		this.sunset = sunset;
	}

	public Integer getSunset(){
		return sunset;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setPopulation(Integer population){
		this.population = population;
	}

	public Integer getPopulation(){
		return population;
	}
}