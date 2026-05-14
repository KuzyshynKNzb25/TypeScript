package com.bit_project.typeweatherwithoutgit.json_objects.JSON_2_DAYS;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HourlyItem{

	@JsonProperty("temp")
	private Float temp;

	@JsonProperty("visibility")
	private Integer visibility;

	@JsonProperty("uvi")
	private Float uvi;

	@JsonProperty("pressure")
	private Integer pressure;

	@JsonProperty("clouds")
	private Integer clouds;

	@JsonProperty("feels_like")
	private Float feelsLike;

	@JsonProperty("wind_gust")
	private Float windGust;

	@JsonProperty("dt")
	private Integer dt;

	@JsonProperty("pop")
	private Float pop;

	@JsonProperty("wind_deg")
	private Integer windDeg;

	@JsonProperty("dew_point")
	private Float dewPoint;

	@JsonProperty("weather")
	private List<WeatherItem> weather;

	@JsonProperty("humidity")
	private Integer humidity;

	@JsonProperty("wind_speed")
	private Float windSpeed;

	public void setTemp(Float temp){
		this.temp = temp;
	}

	public Float getTemp(){
		return temp;
	}

	public void setVisibility(Integer visibility){
		this.visibility = visibility;
	}

	public Integer getVisibility(){
		return visibility;
	}

	public void setUvi(Float uvi){
		this.uvi = uvi;
	}

	public Float getUvi(){
		return uvi;
	}

	public void setPressure(Integer pressure){
		this.pressure = pressure;
	}

	public Integer getPressure(){
		return pressure;
	}

	public void setClouds(Integer clouds){
		this.clouds = clouds;
	}

	public Integer getClouds(){
		return clouds;
	}

	public void setFeelsLike(Float feelsLike){
		this.feelsLike = feelsLike;
	}

	public Float getFeelsLike(){
		return feelsLike;
	}

	public void setWindGust(Float windGust){
		this.windGust = windGust;
	}

	public Float getWindGust(){
		return windGust;
	}

	public void setDt(Integer dt){
		this.dt = dt;
	}

	public Integer getDt(){
		return dt;
	}

	public void setPop(Float pop){
		this.pop = pop;
	}

	public Float getPop(){
		return pop;
	}

	public void setWindDeg(Integer windDeg){
		this.windDeg = windDeg;
	}

	public Integer getWindDeg(){
		return windDeg;
	}

	public void setDewPoint(Float dewPoint){
		this.dewPoint = dewPoint;
	}

	public Float getDewPoint(){
		return dewPoint;
	}

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setHumidity(Integer humidity){
		this.humidity = humidity;
	}

	public Integer getHumidity(){
		return humidity;
	}

	public void setWindSpeed(Float windSpeed){
		this.windSpeed = windSpeed;
	}

	public Float getWindSpeed(){
		return windSpeed;
	}
}