package com.aurospaces.neighbourhood.bean;

public class StudentMarksBean {
	
	protected String studentId ,studentname;
	
	protected String subjectId;
	protected String boardId;
	protected String examTypeId;
	protected String classId;
	protected String sectionId,sectionname;
	protected int id;
	
	
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
		return "StudentMarksBean [studentId=" + studentId + ", subjectId=" + subjectId + ", boardId=" + boardId
				+ ", examTypeId=" + examTypeId + ", classId=" + classId + ", sectionId=" + sectionId + "]";
	}
	
	
	
	
	

}
