package com.model2.mvc.service.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class ViewedProduct {

	///Field
	private String userId;
	private int prodNo;
	private Timestamp timestamp;
	
	//constructor
	public ViewedProduct() {
		
	}
	
	
	//getter, setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "RecentlyViewedProduct [userId=" + userId + ", prodNo=" + prodNo + ", timestamp=" + timestamp + "]";
	}
	
}// end of class 
