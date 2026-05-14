package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds{

	@JsonProperty("all")
	private Integer all;

	public void setAll(Integer all){
		this.all = all;
	}

	public Integer getAll(){
		return all;
	}
}