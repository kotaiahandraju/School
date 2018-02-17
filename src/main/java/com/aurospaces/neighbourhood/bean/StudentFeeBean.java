package com.aurospaces.neighbourhood.bean;

import java.util.Date;

public class StudentFeeBean {
	protected int id   = 0;

	protected Date createdTime ;

	protected Date updatedTime ;
	private String studentId;
	private double  totalFee;
	private double  discount;
	private double  netFee;
	private double  fee;
	private double  remaningFee;
	private String boardName;
	private String className;
	private String medium;
	private String section;
	private String dueFee;
	private String feeType;
	
	
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getDueFee() {
		return dueFee;
	}
	public void setDueFee(String dueFee) {
		this.dueFee = dueFee;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getNetFee() {
		return netFee;
	}
	public void setNetFee(double netFee) {
		this.netFee = netFee;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getRemaningFee() {
		return remaningFee;
	}
	public void setRemaningFee(double remaningFee) {
		this.remaningFee = remaningFee;
	}
	
	
}
