package com.bit_project.typeweatherwithoutgit.json_objects.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds{

	@JsonProperty("all")
	private Integer all;

	public Integer getAll(){
		return all;
	}
}