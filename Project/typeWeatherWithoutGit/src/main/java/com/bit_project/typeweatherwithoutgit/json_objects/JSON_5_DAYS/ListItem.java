package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListItem{

	@JsonProperty("dt")
	private Integer dt;

	@JsonProperty("pop")
	private Integer pop;

	@JsonProperty("visibility")
	private Integer visibility;

	@JsonProperty("dt_txt")
	private String dtTxt;

	@JsonProperty("weather")
	private List<WeatherItem> weather;

	@JsonProperty("main")
	private Main main;

	@JsonProperty("clouds")
	private Clouds clouds;

	@JsonProperty("sys")
	private Sys sys;

	@JsonProperty("wind")
	private Wind wind;

	@JsonProperty("snow")
	private Snow snow;

	public void setDt(Integer dt){
		this.dt = dt;
	}

	public Integer getDt(){
		return dt;
	}

	public void setPop(Integer pop){
		this.pop = pop;
	}

	public Integer getPop(){
		return pop;
	}

	public void setVisibility(Integer visibility){
		this.visibility = visibility;
	}

	public Integer getVisibility(){
		return visibility;
	}

	public void setDtTxt(String dtTxt){
		this.dtTxt = dtTxt;
	}

	public String getDtTxt(){
		return dtTxt;
	}

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	public void setClouds(Clouds clouds){
		this.clouds = clouds;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public void setSys(Sys sys){
		this.sys = sys;
	}

	public Sys getSys(){
		return sys;
	}

	public void setWind(Wind wind){
		this.wind = wind;
	}

	public Wind getWind(){
		return wind;
	}

	public void setSnow(Snow snow){
		this.snow = snow;
	}

	public Snow getSnow(){
		return snow;
	}
}