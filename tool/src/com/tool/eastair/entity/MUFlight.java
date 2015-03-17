package com.tool.eastair.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class MUFlight {
		private List<MUSegment> flightList = new ArrayList<MUSegment>();
		private List<MUProject> productInfoList = new ArrayList<MUProject>();
		public List<MUSegment> getFlightList() {
			return flightList;
		}
		public void setFlightList(List<MUSegment> flightList) {
			this.flightList = flightList;
		}
		public List<MUProject> getProductInfoList() {
			return productInfoList;
		}
		public void setProductInfoList(List<MUProject> productInfoList) {
			this.productInfoList = productInfoList;
		}
		
		

}
