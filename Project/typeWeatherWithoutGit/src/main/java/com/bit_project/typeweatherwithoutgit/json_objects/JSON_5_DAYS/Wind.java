package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind{

	@JsonProperty("deg")
	private Integer deg;

	@JsonProperty("speed")
	private Float speed;

	@JsonProperty("gust")
	private Float gust;

	public void setDeg(Integer deg){
		this.deg = deg;
	}

	public Integer getDeg(){
		return deg;
	}

	public void setSpeed(Float speed){
		this.speed = speed;
	}

	public Float getSpeed(){
		return speed;
	}

	public void setGust(Float gust){
		this.gust = gust;
	}

	public Float getGust(){
		return gust;
	}
}