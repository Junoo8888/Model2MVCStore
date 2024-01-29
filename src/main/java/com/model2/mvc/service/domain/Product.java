package com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	private String fileName;
	private String manuDate;
	private long price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private String prodTranCode;
	private int stock;
	
	public Product(){
	}
	
	public Product(int stock, int prodNo) {
		this.stock = stock;
		this.prodNo = prodNo;
	}
	
	public String getProdTranCode() {
		return prodTranCode;
	}
	public void setProdTranCode(String prodTranCode) {
		if(prodTranCode==null) {
			this.prodTranCode = "0";
		}else {
			this.prodTranCode = prodTranCode.trim();
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate =manuDate.replace("-", "");
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [fileName=" + fileName + ", manuDate=" + manuDate + ", price=" + price + ", prodDetail="
				+ prodDetail + ", prodName=" + prodName + ", prodNo=" + prodNo + ", regDate=" + regDate
				+ ", prodTranCode=" + prodTranCode + ", stock=" + stock + "]";
	}

		
}