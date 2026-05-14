package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow{

	@JsonProperty("3h")
	private Object jsonMember3h;

	public void setJsonMember3h(Object jsonMember3h){
		this.jsonMember3h = jsonMember3h;
	}

	public Object getJsonMember3h(){
		return jsonMember3h;
	}
}