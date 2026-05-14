package com.bit_project.typeweatherwithoutgit.json_objects.JSON_8_DAYS;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyItem{

	@JsonProperty("moonset")
	private Integer moonset;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("sunrise")
	private Integer sunrise;

	@JsonProperty("temp")
	private Temp temp;

	@JsonProperty("moon_phase")
	private Float moonPhase;

	@JsonProperty("uvi")
	private Float uvi;

	@JsonProperty("moonrise")
	private Integer moonrise;

	@JsonProperty("pressure")
	private Integer pressure;

	@JsonProperty("clouds")
	private Integer clouds;

	@JsonProperty("feels_like")
	private FeelsLike feelsLike;

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

	@JsonProperty("sunset")
	private Integer sunset;

	@JsonProperty("weather")
	private List<WeatherItem> weather;

	@JsonProperty("humidity")
	private Integer humidity;

	@JsonProperty("wind_speed")
	private Float windSpeed;

	@JsonProperty("rain")
	private Float rain;

	public void setMoonset(Integer moonset){
		this.moonset = moonset;
	}

	public Integer getMoonset(){
		return moonset;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setSunrise(Integer sunrise){
		this.sunrise = sunrise;
	}

	public Integer getSunrise(){
		return sunrise;
	}

	public void setTemp(Temp temp){
		this.temp = temp;
	}

	public Temp getTemp(){
		return temp;
	}

	public void setMoonPhase(Float moonPhase){
		this.moonPhase = moonPhase;
	}

	public Float getMoonPhase(){
		return moonPhase;
	}

	public void setUvi(Float uvi){
		this.uvi = uvi;
	}

	public Float getUvi(){
		return uvi;
	}

	public void setMoonrise(Integer moonrise){
		this.moonrise = moonrise;
	}

	public Integer getMoonrise(){
		return moonrise;
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

	public void setFeelsLike(FeelsLike feelsLike){
		this.feelsLike = feelsLike;
	}

	public FeelsLike getFeelsLike(){
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

	public void setSunset(Integer sunset){
		this.sunset = sunset;
	}

	public Integer getSunset(){
		return sunset;
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

	public void setRain(Float rain){
		this.rain = rain;
	}

	public Float getRain(){
		return rain;
	}
}