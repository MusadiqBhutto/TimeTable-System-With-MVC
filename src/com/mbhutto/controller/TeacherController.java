package com.mbhutto.controller;

import java.sql.SQLException;

import com.mbhutto.model.TeacherDataOperations;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.Teacher;

public class TeacherController
{
	    // Add Record Method call the addTeacher method of TeachersDataBase
		public long addRecord(Teacher teacher) throws SQLException, ClassNotFoundException {
			return (TeacherDataOperations.addTeacher(teacher));
		}
		
		// Update Record Method call the updateTeacher method of TeachersDataBase
		public int updateRecord(Teacher teacher) throws SQLException, ClassNotFoundException {
			return (TeacherDataOperations.updateTeacher(teacher));
		}

		// deleteRecord() Method call the deleteTeacher method of TeachersDataBase
	    public int deleteRecord(Teacher teacher) throws SQLException, ClassNotFoundException {
			return (TeacherDataOperations.deleteTeacher(teacher));
		}
		
		public Result getRecords() throws SQLException, ClassNotFoundException {

			return TeacherDataOperations.getTeachers();
		}
		
		/*
		 * // search Record() Method call the searchTeacher method of TeachersDataBase
		 * protected Teacher searchRecord() throws SQLException, ClassNotFoundException
		 * { String[] values = TeacherDataOperations.searchTeacher(this.teacherName);
		 * return new Teacher( Long.parseLong(values[0]), new
		 * Department(Long.parseLong(values[11]), values[12], values[13],
		 * Integer.parseInt(values[14]), Integer.parseInt(values[15])),
		 * values[2],values[3],values[4],values[5],values[6],values[7],(values[8]),
		 * values[9] ,Byte.parseByte(values[10]) ); }
		 */


}
