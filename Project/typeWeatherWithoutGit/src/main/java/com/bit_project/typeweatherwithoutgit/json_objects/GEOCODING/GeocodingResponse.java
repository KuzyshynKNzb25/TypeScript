package com.bit_project.typeweatherwithoutgit.json_objects.GEOCODING;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocodingResponse{

	@JsonProperty("GeocodingResponse")
	private List<GeocodingResponseItem> geocodingResponse;

	public List<GeocodingResponseItem> getGeocodingResponse(){
		return geocodingResponse;
	}
}