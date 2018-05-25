package com.aurospaces.neighbourhood.bean;

import java.util.Date;

public class ClassBean {
	protected int id   = 0;

	protected Date createdTime ;

	protected Date updatedTime ;
	private String boardId;
	private String mediumId;
	private String section;
	private String className;
	private double fee;
	private double admissionFee;
	private double tutionFee;
	private double transportationFee;
	private double hostelFee;
	private double stationaryFee;
	public double getAdmissionFee() {
		return admissionFee;
	}
	public void setAdmissionFee(double admissionFee) {
		this.admissionFee = admissionFee;
	}
	public double getTutionFee() {
		return tutionFee;
	}
	public void setTutionFee(double tutionFee) {
		this.tutionFee = tutionFee;
	}
	public double getTransportationFee() {
		return transportationFee;
	}
	public void setTransportationFee(double transportationFee) {
		this.transportationFee = transportationFee;
	}
	public double getHostelFee() {
		return hostelFee;
	}
	public void setHostelFee(double hostelFee) {
		this.hostelFee = hostelFee;
	}
	public double getStationaryFee() {
		return stationaryFee;
	}
	public void setStationaryFee(double stationaryFee) {
		this.stationaryFee = stationaryFee;
	}
	
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
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
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getMediumId() {
		return mediumId;
	}
	public void setMediumId(String mediumId) {
		this.mediumId = mediumId;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	}
