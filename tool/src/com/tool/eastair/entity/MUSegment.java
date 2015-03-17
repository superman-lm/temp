package com.tool.eastair.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class MUSegment {
	
	private String airlineName;
	private String arrCd;
	private String arrCity;
	private String arrCityNm;
	private String arrNm;
	private String arrTerm;
	private String arrTime;
	private String carrier;
	private String codeShare;
	private String deptCd;
	private String deptCity;
	private String deptCityNm;
	private String deptNm;
	private String deptTime;
	private String duration;
	private String flightNo;
	private String shareAirlineName;
	private String shareFlightNo;
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getArrCd() {
		return arrCd;
	}
	public void setArrCd(String arrCd) {
		this.arrCd = arrCd;
	}
	public String getArrCity() {
		return arrCity;
	}
	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}
	public String getArrCityNm() {
		return arrCityNm;
	}
	public void setArrCityNm(String arrCityNm) {
		this.arrCityNm = arrCityNm;
	}
	public String getArrNm() {
		return arrNm;
	}
	public void setArrNm(String arrNm) {
		this.arrNm = arrNm;
	}
	public String getArrTerm() {
		return arrTerm;
	}
	public void setArrTerm(String arrTerm) {
		this.arrTerm = arrTerm;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCodeShare() {
		return codeShare;
	}
	public void setCodeShare(String codeShare) {
		this.codeShare = codeShare;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getDeptCity() {
		return deptCity;
	}
	public void setDeptCity(String deptCity) {
		this.deptCity = deptCity;
	}
	public String getDeptCityNm() {
		return deptCityNm;
	}
	public void setDeptCityNm(String deptCityNm) {
		this.deptCityNm = deptCityNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getDeptTime() {
		return deptTime;
	}
	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getShareAirlineName() {
		return shareAirlineName;
	}
	public void setShareAirlineName(String shareAirlineName) {
		this.shareAirlineName = shareAirlineName;
	}
	public String getShareFlightNo() {
		return shareFlightNo;
	}
	public void setShareFlightNo(String shareFlightNo) {
		this.shareFlightNo = shareFlightNo;
	}
	
	
	
	
	

}
