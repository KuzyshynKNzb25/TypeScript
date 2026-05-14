package com.bit_project.typeweatherwithoutgit.json_objects.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind{

	@JsonProperty("deg")
	private Integer deg;

	@JsonProperty("speed")
	private Object speed;

	@JsonProperty("gust")
	private Object gust;

	public Integer getDeg(){
		return deg;
	}

	public Object getSpeed(){
		return speed;
	}

	public Object getGust(){
		return gust;
	}
}