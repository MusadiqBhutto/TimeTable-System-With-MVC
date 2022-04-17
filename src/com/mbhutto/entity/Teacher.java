package com.mbhutto.entity;

import java.sql.ResultSet;

public class Teacher 
{
	private long     teacherId				;
	private Department department       	;
	private String   teacherName  	  	    ;
	private String   teacherNicNumber  		;
	private String   teacherHomePhone		;
	private String   teacherOfficePhone		;
	private String   teacherMobilePhone		;
	private String   teacherEmail			;
	private String   teacherQualifications	;
	private String   teacherDesignation		;
	private byte     teacherGrade           ;
	private ResultSet resultSet 			;
	
	

	// Constructor with name only, called at the time of delete record.
	public Teacher(String teacherName)
	{
		this.teacherName = teacherName;
	}

	// Constructor with all fields/ Properties
	public Teacher(long teacherId, Department department, String teacherName, String teacherNicNumber, String teacherHomePhone, String teacherOfficePhone, String teacherMobilePhone, String teacherEmail, String teacherQualifications, String teacherDesignation, byte teacherGrade)
	{
		this.teacherId = teacherId;
		this.department = department;
		this.teacherName = teacherName;
		this.teacherNicNumber = teacherNicNumber;
		this.teacherHomePhone = teacherHomePhone;
		this.teacherOfficePhone = teacherOfficePhone;
		this.teacherMobilePhone = teacherMobilePhone;
		this.teacherEmail = teacherEmail;
		this.teacherQualifications = teacherQualifications;
		this.teacherDesignation = teacherDesignation;
		this.teacherGrade = teacherGrade;

	}

	public Teacher(long teacherId, String teacherName, String teacherNicNumber, String teacherHomePhone, String teacherOfficePhone, String teacherMobilePhone, String teacherEmail, String teacherQualifications, String teacherDesignation, byte teacherGrade)
	{
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherNicNumber = teacherNicNumber;
		this.teacherHomePhone = teacherHomePhone;
		this.teacherOfficePhone = teacherOfficePhone;
		this.teacherMobilePhone = teacherMobilePhone;
		this.teacherEmail = teacherEmail;
		this.teacherQualifications = teacherQualifications;
		this.teacherDesignation = teacherDesignation;
		this.teacherGrade = teacherGrade;

	}

	public Teacher() {

	}



	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherNicNumber() {
		return teacherNicNumber;
	}

	public void setTeacherNicNumber(String teacherNicNumber) {
		this.teacherNicNumber = teacherNicNumber;
	}

	public String getTeacherHomePhone() {
		return teacherHomePhone;
	}

	public void setTeacherHomePhone(String teacherHomePhone) {
		this.teacherHomePhone = teacherHomePhone;
	}

	public String getTeacherOfficePhone() {
		return teacherOfficePhone;
	}

	public void setTeacherOfficePhone(String teacherOfficePhone) {
		this.teacherOfficePhone = teacherOfficePhone;
	}

	public String getTeacherMobilePhone() {
		return teacherMobilePhone;
	}

	public void setTeacherMobilePhone(String teacherMobilePhone) {
		this.teacherMobilePhone = teacherMobilePhone;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherQualifications() {
		return teacherQualifications;
	}

	public void setTeacherQualifications(String teacherQualifications) {
		this.teacherQualifications = teacherQualifications;
	}

	public String getTeacherDesignation() {
		return teacherDesignation;
	}

	public void setTeacherDesignation(String teacherDesignation) {
		this.teacherDesignation = teacherDesignation;
	}

	public byte getTeacherGrade() {
		return teacherGrade;
	}

	public void setTeacherGrade(byte teacherGrade) {
		this.teacherGrade = teacherGrade;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	@Override
	public String toString() {
		return
				 teacherId + ". " +teacherName;

	}


}
