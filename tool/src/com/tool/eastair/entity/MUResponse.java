package com.tool.eastair.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class MUResponse {
	
	private List<MUItem> tripItemList = new ArrayList<MUItem>();

	public List<MUItem> getTripItemList() {
		return tripItemList;
	}

	public void setTripItemList(List<MUItem> tripItemList) {
		this.tripItemList = tripItemList;
	}
	

}
