package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord{

	@JsonProperty("lon")
	private Object lon;

	@JsonProperty("lat")
	private Object lat;

	public void setLon(Object lon){
		this.lon = lon;
	}

	public Object getLon(){
		return lon;
	}

	public void setLat(Object lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}
}