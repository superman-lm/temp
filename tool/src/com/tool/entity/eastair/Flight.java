package com.tool.entity.eastair;

import java.util.ArrayList;
import java.util.List;

public class Flight {
	private String depDate;// ��������
	private String arrDate;// ��������
	private String depPlace;// ������
	private String arrPlace;// Ŀ�ĵ�
	private String adtPrice;// ����Ʊ���
	private String chdPrice;// ��ͯƱ���
	private String tax;// ˰
	private String rule;// Ʊ��
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
