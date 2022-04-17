package com.mbhutto.controller;

import java.sql.SQLException;

import com.mbhutto.entity.Result;
import com.mbhutto.entity.TimeTable;
import com.mbhutto.model.TimeTableDataOperations;

public class TimeTableController
{
	 public Result getDistinctDepartmentsForTimeTable() throws SQLException, ClassNotFoundException {
	        return TimeTableDataOperations.getDistinctDepartmentsForTimeTable() ;
	    }

	    public Result getFreeClasses(TimeTable timeTables) throws SQLException, ClassNotFoundException
	    {
	         Result result = TimeTableDataOperations.getFreeClassesForGivenDepartmentTermSection(timeTables.getDepartmentTermSectionId());
	         String timeTable[][] = new String[result.length][7];

	         for(int i=0; i< result.length; i++)
	         {
	             timeTable[i][0] = result.records[i][0]; //id
	             timeTable[i][1] = result.records[i][1]; //DTS id
	             timeTable[i][2] = result.records[i][2]; //SDTS id
	             timeTable[i][3] = result.records[i][3]; //day
	             timeTable[i][4] = result.records[i][4]; //class
	             timeTable[i][5] = result.records[i][5]; //type
	             timeTable[i][6] = result.records[i][6]; //allocated
	         }

	         return result;
	    }

	    

	    public Result getDepartmentTermsForTimeTable(long selectedDepartmentId) throws SQLException, ClassNotFoundException {
	        return TimeTableDataOperations.getDepartmentTermsForTimeTable(selectedDepartmentId);
	    }

	    public Result getDepartmentTermsSectionsForTimeTable(long selectedDepartmentId, long selectedTermId) throws SQLException, ClassNotFoundException {
	        return TimeTableDataOperations.getDepartmentTermsSectionsForTimeTable(selectedDepartmentId, selectedTermId);
	    }

	    public Result getTimeTable(long departmentId, long selectedTerm, long selectedSection) throws SQLException, ClassNotFoundException {
	        return  TimeTableDataOperations.getTimeTable( departmentId,  selectedTerm,  selectedSection);
	    }
	    
	    public Result getDistinctSubjectsTimeTable(long departmentId, long termId, long sectionId) throws SQLException, ClassNotFoundException {
	        return  TimeTableDataOperations.getDistinctSubjectsTimeTable( departmentId,  termId,  sectionId);
	    }

}
