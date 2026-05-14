package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main{

	@JsonProperty("temp")
	private Float temp;

	@JsonProperty("temp_min")
	private Float tempMin;

	@JsonProperty("grnd_level")
	private Integer grndLevel;

	@JsonProperty("temp_kf")
	private Float tempKf;

	@JsonProperty("humidity")
	private Integer humidity;

	@JsonProperty("pressure")
	private Integer pressure;

	@JsonProperty("sea_level")
	private Integer seaLevel;

	@JsonProperty("feels_like")
	private Float feelsLike;

	@JsonProperty("temp_max")
	private Float tempMax;

	public void setTemp(Float temp){
		this.temp = temp;
	}

	public Float getTemp(){
		return temp;
	}

	public void setTempMin(Float tempMin){
		this.tempMin = tempMin;
	}

	public Float getTempMin(){
		return tempMin;
	}

	public void setGrndLevel(Integer grndLevel){
		this.grndLevel = grndLevel;
	}

	public Integer getGrndLevel(){
		return grndLevel;
	}

	public void setTempKf(Float tempKf){
		this.tempKf = tempKf;
	}

	public Float getTempKf(){
		return tempKf;
	}

	public void setHumidity(Integer humidity){
		this.humidity = humidity;
	}

	public Integer getHumidity(){
		return humidity;
	}

	public void setPressure(Integer pressure){
		this.pressure = pressure;
	}

	public Integer getPressure(){
		return pressure;
	}

	public void setSeaLevel(Integer seaLevel){
		this.seaLevel = seaLevel;
	}

	public Integer getSeaLevel(){
		return seaLevel;
	}

	public void setFeelsLike(Float feelsLike){
		this.feelsLike = feelsLike;
	}

	public Float getFeelsLike(){
		return feelsLike;
	}

	public void setTempMax(Float tempMax){
		this.tempMax = tempMax;
	}

	public Float getTempMax(){
		return tempMax;
	}
}