package com.bit_project.typeweatherwithoutgit.json_objects.GEOCODING;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class GeocodingResponseItem{

	@JsonProperty("local_names")
	private Map<String, String>  localNames;

	@JsonProperty("country")
	private String country;

	@JsonProperty("name")
	private String name;

	@JsonProperty("lon")
	private float lon;

	@JsonProperty("state")
	private String state;

	@JsonProperty("lat")
	private float lat;

	public Map<String, String> getLocalNames(){
		return localNames;
	}

	public String getCountry(){
		return country;
	}

	public String getName(){
		return name;
	}

	public float getLon(){
		return lon;
	}

	public String getState(){
		return state;
	}

	public float getLat(){
		return lat;
	}
}