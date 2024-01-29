package com.model2.mvc.service.domain;

public class Grade {
	
	///Field
	private int gradeId;
	private String gradeName;
	private int discount;
	
	///Constructor
	public Grade() {
	}
	
	///Method
	///Getter, Setter
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", gradeName=" + gradeName + ", discount=" + discount + "]";
	}
	
}// end of class 
