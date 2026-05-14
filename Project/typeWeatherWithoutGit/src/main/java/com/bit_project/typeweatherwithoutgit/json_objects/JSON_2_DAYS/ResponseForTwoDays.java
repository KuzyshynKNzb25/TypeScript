package com.bit_project.typeweatherwithoutgit.json_objects.JSON_2_DAYS;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseForTwoDays {

	@JsonProperty("timezone")
	private String timezone;

	@JsonProperty("timezone_offset")
	private Integer timezoneOffset;

	@JsonProperty("lon")
	private Float lon;

	@JsonProperty("hourly")
	private List<HourlyItem> hourly;

	@JsonProperty("lat")
	private Float lat;

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setTimezoneOffset(Integer timezoneOffset){
		this.timezoneOffset = timezoneOffset;
	}

	public Integer getTimezoneOffset(){
		return timezoneOffset;
	}

	public void setLon(Float lon){
		this.lon = lon;
	}

	public Object getLon(){
		return lon;
	}

	public void setHourly(List<HourlyItem> hourly){
		this.hourly = hourly;
	}

	public List<HourlyItem> getHourly(){
		return hourly;
	}

	public void setLat(Float lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}
}