package com.mbhutto.controller;

import java.sql.SQLException;

import com.mbhutto.model.DepartmentDataOperations;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;

public class DepartmentController
{
	
	// Add Record Method call the add Departement method
    public long addRecord(Department department) throws SQLException, ClassNotFoundException {
		return (DepartmentDataOperations.addDepartment(department));
	}
    
    // Update Record Method call the update Departement method 
    public int updateRecord(Department department) throws SQLException, ClassNotFoundException
	{
		return (DepartmentDataOperations.updateDepartment(department));
	}

	// delete Record Method call the deleteDepartement method
	public int deleteRecord(Department department) throws SQLException, ClassNotFoundException {
		return (DepartmentDataOperations.deleteDepartment(department));
	}

	// get Records Method call the viewDepartements method
	public Result getRecords() throws SQLException, ClassNotFoundException{
		return DepartmentDataOperations.viewDepartments();
	}

	// getDeptTeachers calls getDeptTeachers
	// This method is used to get the department's teachers
	public static Result getDeptTeachers(Department department) throws SQLException, ClassNotFoundException {
		return DepartmentDataOperations.getDeptTeachers(department.getDepartmentID());
	}

	// getDeptTermSubjects calls getDeptTermSubjects
	// This method is used to get the subjects that are taught in different terms of department
	public static Result getDeptTermSubjects(Long id, int terms) throws SQLException, ClassNotFoundException {
		return DepartmentDataOperations.getDeptTermSubjects(id, terms);
	}
	

}
