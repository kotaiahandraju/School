package com.aurospaces.neighbourhood.bean;

public class StudentMarksBean {
	
	protected String studentId;
	protected String subjectId;
	protected String boardId;
	protected String examTypeId;
	protected String classId;
	
	
	
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
				+ ", examTypeId=" + examTypeId + ", classId=" + classId + "]";
	}
	
	
	
	
	

}
