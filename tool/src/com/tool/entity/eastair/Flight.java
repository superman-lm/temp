package com.tool.entity.eastair;

import java.util.ArrayList;
import java.util.List;

public class Flight {
	private String depDate;// 出发日期
	private String arrDate;// 到达日期
	private String depPlace;// 出发地
	private String arrPlace;// 目的地
	private String adtPrice;// 成人票面价
	private String chdPrice;// 儿童票面价
	private String tax;// 税
	private String rule;// 票规
	private boolean isDirect = Boolean.TRUE;;
	

	public boolean isDirect() {
		return isDirect;
	}

	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}

	List<Leg> legs = new ArrayList<Leg>();

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getArrDate() {
		return arrDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}

	public String getDepPlace() {
		return depPlace;
	}

	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}

	public String getArrPlace() {
		return arrPlace;
	}

	public void setArrPlace(String arrPlace) {
		this.arrPlace = arrPlace;
	}

	

	public String getAdtPrice() {
		return adtPrice;
	}

	public void setAdtPrice(String adtPrice) {
		this.adtPrice = adtPrice;
	}

	public String getChdPrice() {
		return chdPrice;
	}

	public void setChdPrice(String chdPrice) {
		this.chdPrice = chdPrice;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	
	
}
