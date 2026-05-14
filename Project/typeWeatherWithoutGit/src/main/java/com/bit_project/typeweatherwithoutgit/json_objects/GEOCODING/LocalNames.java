package com.bit_project.typeweatherwithoutgit.json_objects.GEOCODING;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class LocalNames{

	Map<String, String> localNames;

	@JsonProperty("de")
	private String de;

	@JsonProperty("ru")
	private String ru;

	@JsonProperty("uk")
	private String uk;

	@JsonProperty("en")
	private String en;

	@JsonProperty("pl")
	private String pl;

	@JsonProperty("fr")
	private String fr;

	public String getDe(){
		return de;
	}

	public String getRu(){
		return ru;
	}

	public String getUk(){
		return uk;
	}

	public String getEn(){
		return en;
	}

	public String getPl(){
		return pl;
	}

	public String getFr(){
		return fr;
	}

	public Map<String, String> getLocalNames() {
		return localNames;
	}
}