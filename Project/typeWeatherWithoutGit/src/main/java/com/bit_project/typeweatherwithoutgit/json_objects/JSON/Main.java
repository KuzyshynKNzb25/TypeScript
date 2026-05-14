package com.bit_project.typeweatherwithoutgit.json_objects.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main{

	@JsonProperty("temp")
	private Object temp;

	@JsonProperty("temp_min")
	private Object tempMin;

	@JsonProperty("grnd_level")
	private Integer grndLevel;

	@JsonProperty("humidity")
	private Integer humidity;

	@JsonProperty("pressure")
	private Integer pressure;

	@JsonProperty("sea_level")
	private Integer seaLevel;

	@JsonProperty("feels_like")
	private Object feelsLike;

	@JsonProperty("temp_max")
	private Object tempMax;

	public Object getTemp(){
		return temp;
	}

	public Object getTempMin(){
		return tempMin;
	}

	public Integer getGrndLevel(){
		return grndLevel;
	}

	public Integer getHumidity(){
		return humidity;
	}

	public Integer getPressure(){
		return pressure;
	}

	public Integer getSeaLevel(){
		return seaLevel;
	}

	public Object getFeelsLike(){
		return feelsLike;
	}

	public Object getTempMax(){
		return tempMax;
	}
}