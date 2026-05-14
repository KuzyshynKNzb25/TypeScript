package com.bit_project.typeweatherwithoutgit.json_objects.JSON_8_DAYS;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseForEightDays{

	@JsonProperty("timezone")
	private String timezone;

	@JsonProperty("timezone_offset")
	private Integer timezoneOffset;

	@JsonProperty("daily")
	private List<DailyItem> daily;

	@JsonProperty("lon")
	private Object lon;

	@JsonProperty("lat")
	private Object lat;

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

	public void setDaily(List<DailyItem> daily){
		this.daily = daily;
	}

	public List<DailyItem> getDaily(){
		return daily;
	}

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