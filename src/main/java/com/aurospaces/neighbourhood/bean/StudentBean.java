package com.aurospaces.neighbourhood.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class StudentBean {
 protected String  name,boardName,medium,className,section,rollNum,admissionNum,fatherName,mobile,alternativeMobile,email,blodgroup,gender,region,address,previousInstitue,caste,acomitation,buspesility,busroute,religion;
 protected int id   = 0;
 protected String imagePath;
protected double totalFee;
protected double discountFee;
String discountFee1;
String dob2;
protected Date dob3;
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

public Date getDob3() {
	return dob3;
}

public void setDob3(Date dob3) {
	this.dob3 = dob3;
}

public String getDob2() {
	return dob2;
}

public void setDob2(String dob2) {
	this.dob2 = dob2;
}

public String getDiscountFee1() {
	return discountFee1;
}

public void setDiscountFee1(String discountFee1) {
	this.discountFee1 = discountFee1;
}

protected double netFee;

 
 public double getTotalFee() {
	return totalFee;
}

public void setTotalFee(double totalFee) {
	this.totalFee = totalFee;
}

public double getDiscountFee() {
	return discountFee;
}

public void setDiscountFee(double discountFee) {
	this.discountFee = discountFee;
}

public double getNetFee() {
	return netFee;
}

public void setNetFee(double netFee) {
	this.netFee = netFee;
}

public String getImagePath() {
	return imagePath;
}

public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}

MultipartFile imageName;

	public MultipartFile getImageName() {
	return imageName;
}

public void setImageName(MultipartFile imageName) {
	this.imageName = imageName;
}

	protected Date createdTime ;

	protected Date updatedTime ;
	protected Date dob ;
	String dob1;
public String getDob1() {
		return dob1;
	}

	public void setDob1(String dob1) {
		this.dob1 = dob1;
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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getBoardName() {
	return boardName;
}

public void setBoardName(String boardName) {
	this.boardName = boardName;
}

public String getMedium() {
	return medium;
}

public void setMedium(String medium) {
	this.medium = medium;
}

public String getClassName() {
	return className;
}

public void setClassName(String className) {
	this.className = className;
}

public String getSection() {
	return section;
}

public void setSection(String section) {
	this.section = section;
}

public String getRollNum() {
	return rollNum;
}

public void setRollNum(String rollNum) {
	this.rollNum = rollNum;
}

public String getAdmissionNum() {
	return admissionNum;
}

public void setAdmissionNum(String admissionNum) {
	this.admissionNum = admissionNum;
}

public String getFatherName() {
	return fatherName;
}

public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getAlternativeMobile() {
	return alternativeMobile;
}

public void setAlternativeMobile(String alternativeMobile) {
	this.alternativeMobile = alternativeMobile;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getBlodgroup() {
	return blodgroup;
}

public void setBlodgroup(String blodgroup) {
	this.blodgroup = blodgroup;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}


public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPreviousInstitue() {
	return previousInstitue;
}

public void setPreviousInstitue(String previousInstitue) {
	this.previousInstitue = previousInstitue;
}

public String getCaste() {
	return caste;
}

public void setCaste(String caste) {
	this.caste = caste;
}

public String getAcomitation() {
	return acomitation;
}

public void setAcomitation(String acomitation) {
	this.acomitation = acomitation;
}

public String getBuspesility() {
	return buspesility;
}

public void setBuspesility(String buspesility) {
	this.buspesility = buspesility;
}

public String getBusroute() {
	return busroute;
}

public void setBusroute(String busroute) {
	this.busroute = busroute;
}

public String getReligion() {
	return religion;
}

public void setReligion(String religion) {
	this.religion = religion;
}

}
