package com.stormtech.demo.models;

public class studentModel {
	
	private Integer studentId;
	private String studentNames;

	public studentModel(Integer studentId, String studentNames) {
		
		this.studentId = studentId;
		this.studentNames = studentNames;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(String studentNames) {
		this.studentNames = studentNames;
	}

}