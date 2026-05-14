package com.bit_project.typeweatherwithoutgit.json_objects.JSON_8_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temp{

	@JsonProperty("min")
	private Float min;

	@JsonProperty("max")
	private Float max;

	@JsonProperty("eve")
	private Float eve;

	@JsonProperty("night")
	private Float night;

	@JsonProperty("day")
	private Float day;

	@JsonProperty("morn")
	private Float morn;

	public void setMin(Float min){
		this.min = min;
	}

	public Float getMin(){
		return min;
	}

	public void setMax(Float max){
		this.max = max;
	}

	public Float getMax(){
		return max;
	}

	public void setEve(Float eve){
		this.eve = eve;
	}

	public Float getEve(){
		return eve;
	}

	public void setNight(Float night){
		this.night = night;
	}

	public Float getNight(){
		return night;
	}

	public void setDay(Float day){
		this.day = day;
	}

	public Float getDay(){
		return day;
	}

	public void setMorn(Float morn){
		this.morn = morn;
	}

	public Float getMorn(){
		return morn;
	}
}