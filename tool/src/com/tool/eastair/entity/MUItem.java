package com.tool.eastair.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class MUItem {
	
	private List<MUFlight> airRoutingList = new ArrayList<MUFlight>();

	public List<MUFlight> getAirRoutingList() {
		return airRoutingList;
	}

	public void setAirRoutingList(List<MUFlight> airRoutingList) {
		this.airRoutingList = airRoutingList;
	}
	

}
