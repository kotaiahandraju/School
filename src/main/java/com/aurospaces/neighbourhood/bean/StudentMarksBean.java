package com.aurospaces.neighbourhood.bean;

import java.util.Date;

public class StudentMarksBean {
	
	protected String studentId ,studentname, studentMarks;
	
	protected String subjectId, subjectMaxMarks;
	protected String boardId;
	protected String examTypeId;
	protected String classId;
	protected String sectionId,sectionname;
	protected Date createdTime;
	protected Date updatedTime;
	
	protected String medium;
	protected int id;
	
	
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getStudentMarks() {
		return studentMarks;
	}
	public String getSubjectMaxMarks() {
		return subjectMaxMarks;
	}
	public void setStudentMarks(String studentMarks) {
		this.studentMarks = studentMarks;
	}
	public void setSubjectMaxMarks(String subjectMaxMarks) {
		this.subjectMaxMarks = subjectMaxMarks;
	}
	
	
	
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	
	
	
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getSubjectI() {
		return subjectId;
	}
	public void setSubjectI(String subjectI) {
		this.subjectId = subjectI;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(String examTypeId) {
		this.examTypeId = examTypeId;
	}
	@Override
	public String toString() {
		return "StudentMarksBean [studentId=" + studentId + ", studentname=" + studentname + ", studentMarks="
				+ studentMarks + ", subjectId=" + subjectId + ", subjectMaxMarks=" + subjectMaxMarks + ", boardId="
				+ boardId + ", examTypeId=" + examTypeId + ", classId=" + classId + ", sectionId=" + sectionId
				+ ", sectionname=" + sectionname + ", medium=" + medium + ", id=" + id + "]";
	}
	
	
	
	
	

}
