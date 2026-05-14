package com.bit_project.typeweatherwithoutgit.json_objects.JSON_5_DAYS;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseForFive {

	@JsonProperty("city")
	private City city;

	@JsonProperty("cnt")
	private Integer cnt;

	@JsonProperty("cod")
	private String cod;

	@JsonProperty("message")
	private Integer message;

	@JsonProperty("list")
	private List<ListItem> list;

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setCnt(Integer cnt){
		this.cnt = cnt;
	}

	public Integer getCnt(){
		return cnt;
	}

	public void setCod(String cod){
		this.cod = cod;
	}

	public String getCod(){
		return cod;
	}

	public void setMessage(Integer message){
		this.message = message;
	}

	public Integer getMessage(){
		return message;
	}

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}
}