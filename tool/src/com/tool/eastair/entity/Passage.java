package com.tool.eastair.entity;

public class Passage {
	
	private String type;
	private String name;
	private String phone;
	private String number;
	private String cardType;
	private String cardNo;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Passage(String type, String name, String phone, String number, String cardType, String cardNo) {
		super();
		this.type = type;
		this.name = name;
		this.phone = phone;
		this.number = number;
		this.cardType = cardType;
		this.cardNo = cardNo;
	}
	

}
