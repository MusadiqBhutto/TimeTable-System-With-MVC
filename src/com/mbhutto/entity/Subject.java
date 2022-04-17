package com.mbhutto.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject 
{
	private long 	subjectId							;
	private String	subjectCode							;
	private String	subjectName							;
	private String  subjectShortName 					;
	private int     subjectTheoryMarks 	     			;
	private int     subjectTheoryClassesInWeek 			;
	private int     subjectPracticalMarks 	   			;
	private int     subjectPracticalClassesInWeek	    ;
	private ResultSet resultSet                         ;

    // Constructor first four fields
	public Subject(long subjectId, String subjectCode, String subjectName, String subjectShortName)  throws SQLException
	{
		this.subjectId = subjectId						;
		this.subjectCode = subjectCode					;
		this.subjectName = subjectName					;
		this.subjectShortName = subjectShortName		;
	}

	// Constructor all  fields
	public Subject(long subjectId, String subjectCode, String subjectName, String subjectShortName, int subjectTheoryMarks, int subjectTheoryClassesInWeek, int subjectPracticalMarks, int subjectPracticalClassesInWeek) throws SQLException
	{
		this.subjectId = subjectId															;
		this.subjectCode = subjectCode														;
		this.subjectName = subjectName														;
		this.subjectShortName = subjectShortName											;
		this.subjectTheoryMarks = subjectTheoryMarks										;
		this.subjectTheoryClassesInWeek = subjectTheoryClassesInWeek						;
		this.subjectPracticalMarks = subjectPracticalMarks									;
		this.subjectPracticalClassesInWeek = subjectPracticalClassesInWeek					;
	}

	public Subject()
	{

	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectShortName() {
		return subjectShortName;
	}

	public void setSubjectShortName(String subjectShortName) {
		this.subjectShortName = subjectShortName;
	}

	public int getSubjectTheoryMarks() {
		return subjectTheoryMarks;
	}

	public void setSubjectTheoryMarks(int subjectTheoryMarks) {
		this.subjectTheoryMarks = subjectTheoryMarks;
	}

	public int getSubjectTheoryClassesInWeek() {
		return subjectTheoryClassesInWeek;
	}

	public void setSubjectTheoryClassesInWeek(int subjectTheoryClassesInWeek) {
		this.subjectTheoryClassesInWeek = subjectTheoryClassesInWeek;
	}

	public int getSubjectPracticalMarks() {
		return subjectPracticalMarks;
	}

	public void setSubjectPracticalMarks(int subjectPracticalMarks) {
		this.subjectPracticalMarks = subjectPracticalMarks;
	}

	public int getSubjectPracticalClassesInWeek() {
		return subjectPracticalClassesInWeek;
	}

	public void setSubjectPracticalClassesInWeek(int subjectPracticalClassesInWeek) {
		this.subjectPracticalClassesInWeek = subjectPracticalClassesInWeek;
	}

	@Override
	public String toString()
	{
		return  subjectId +". " + subjectName ;

	}

}
