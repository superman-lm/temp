package com.tool.eastair.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class MUProject {
	
	private String baseCabin;
	private String cabinCode;
	private String comment;
	private String price;
	private String priceAmountAdt;
	private String priceAmountChd;
	private String productNm;
	public String getBaseCabin() {
		return baseCabin;
	}
	public void setBaseCabin(String baseCabin) {
		this.baseCabin = baseCabin;
	}
	public String getCabinCode() {
		return cabinCode;
	}
	public void setCabinCode(String cabinCode) {
		this.cabinCode = cabinCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceAmountAdt() {
		return priceAmountAdt;
	}
	public void setPriceAmountAdt(String priceAmountAdt) {
		this.priceAmountAdt = priceAmountAdt;
	}
	public String getPriceAmountChd() {
		return priceAmountChd;
	}
	public void setPriceAmountChd(String priceAmountChd) {
		this.priceAmountChd = priceAmountChd;
	}
	public String getProductNm() {
		return productNm;
	}
	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}
	
}
