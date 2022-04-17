package com.mbhutto.controller;

import java.sql.SQLException;
import com.mbhutto.model.SubjectDataOperations;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.Subject;

public class SubjectController
{
	// Add Record Method call the addSubject method
    public long addRecord(Subject subject) throws SQLException, ClassNotFoundException {
		return (SubjectDataOperations.addSubject(subject));
	}
    
   // Update Record Method call the updateSubject method update BY ID
    public int updateRecord(Subject subject) throws SQLException, ClassNotFoundException {
		return (SubjectDataOperations.updateSubject(subject));
	}


	// delete Record Method call the deleteSubject method DELETE BY NAME
	public int deleteRecord(Subject subject) throws SQLException, ClassNotFoundException {
		return (SubjectDataOperations.deleteSubject(subject));
	}

	
	// search Record() Method call the searchSubject method SEARCH BY NAME
	protected Subject searchRecord(Subject subject) throws SQLException, ClassNotFoundException
	{
		String[] values  = SubjectDataOperations.searchSubject(subject);
		return new Subject( Long.parseLong(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]));
	}
    
	// getRecords() Method call the getSubjects method SEARCH BY NAME
	public Result getRecords() throws SQLException, ClassNotFoundException
	{
		return SubjectDataOperations.getSubjects();
	}
	
    // getSubjectDepartmentTerms() calls getSubjectsDepartmentsTerms()
    public Result getSubjectDepartmentTerms(Subject subject) throws SQLException, ClassNotFoundException {

		return SubjectDataOperations.getSubjectsDepartmentsTerms(subject);
    }

}
