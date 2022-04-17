package com.mbhutto.entity;

import com.mbhutto.model.DepartmentDataOperations;

import java.sql.* ;

public class Department
{
	private long 	departmentID			;
	private String	departmentName	   		;
	private String  departmentShortName 	;
	private	int	    departmentTerms		    ;
	private int  	departmentSections		;

	public Department()
	{

	}

	public Department(long departmentID)
	{
		this.departmentID = departmentID;
	}

	public Department(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public Department(long departmentID, String departmentName, String departmentShortName, int departmentTerms, int departmentSections)
	{
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.departmentShortName = departmentShortName;
		this.departmentTerms = departmentTerms;
		this.departmentSections = departmentSections;
	}

	public long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(long departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentShortName() {
		return departmentShortName;
	}

	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}

	public int getDepartmentTerms() {
		return departmentTerms;
	}

	public void setDepartmentTerms(int departmentTerms) {
		this.departmentTerms = departmentTerms;
	}

	public int getDepartmentSections() {
		return departmentSections;
	}

	public void setDepartmentSections(int departmentSections) {
		this.departmentSections = departmentSections;
	}


	@Override
	public String toString()
	{
		return departmentID + ". " + departmentName ;
	}
}

