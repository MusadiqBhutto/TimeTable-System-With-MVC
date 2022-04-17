package com.mbhutto.model;

import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public final class TeacherDataOperations
{
	private static ResultSet resultSet ;

	// ADD TEACHER
	public static long addTeacher(Teacher teacher) throws SQLException, ClassNotFoundException 
	{
		long teacherId = -1 ;
		String query = "INSERT INTO teachers" +
				"(department_id, teacher_name, teacher_nic_number, teacher_home_phone, teacher_office_phone," +
				" teacher_mobile_phone, teacher_email, teacher_qualification, teacher_designation, teacher_grade)" +
				" VALUES "+
				"(?, ?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement insertPreparedStatement = Connect.getConnection().prepareStatement(query, RETURN_GENERATED_KEYS)  ;
		insertPreparedStatement.setLong(1, teacher.getDepartment().getDepartmentID())	    ;
		insertPreparedStatement.setString(2,teacher.getTeacherName().trim())				;
		insertPreparedStatement.setString(3,teacher.getTeacherNicNumber().trim())		    ;
		insertPreparedStatement.setString(4,teacher.getTeacherHomePhone().trim())			;
		insertPreparedStatement.setString(5,teacher.getTeacherOfficePhone().trim())		    ;
		insertPreparedStatement.setString(6,teacher.getTeacherMobilePhone().trim())		    ;
		insertPreparedStatement.setString  (7,teacher.getTeacherEmail())					;
		insertPreparedStatement.setString(8,teacher.getTeacherQualifications().trim())	    ;
		insertPreparedStatement.setString(9,teacher.getTeacherDesignation().trim())		    ;
		insertPreparedStatement.setByte  (10,teacher.getTeacherGrade())					    ;
		int inserted = insertPreparedStatement.executeUpdate()	   						    ;

		if(inserted >= 1)
		{
			resultSet = insertPreparedStatement.getGeneratedKeys();
			if (resultSet.next())
			{
				teacherId = resultSet.getLong(1);
			}
			resultSet.close();
		}
		insertPreparedStatement.close()							;

		return teacherId ;
	}
	
	// UPDATE TEACHER
	public static int updateTeacher(Teacher teacher) throws SQLException, ClassNotFoundException
	{
		String query = "UPDATE teachers " +
				        " SET " +
				"department_id = ?, " +
				"teacher_name = ?, " +
				"teacher_nic_number = ?, " +
				"teacher_home_phone = ?, " +
				"teacher_office_phone = ?, " +
				"teacher_mobile_phone = ?, " +
				"teacher_email = ?, " +
				"teacher_qualification = ?, " +
				"teacher_designation = ?, " +
				"teacher_grade = ? "       +
				"WHERE id = ?" ;

		PreparedStatement updatePreparedStatement = Connect.getConnection().prepareStatement(query)	;
		updatePreparedStatement.setLong(1, teacher.getDepartment().getDepartmentID())	    ;
		updatePreparedStatement.setString(2,teacher.getTeacherName().trim())				;
		updatePreparedStatement.setString(3,teacher.getTeacherNicNumber().trim())		    ;
		updatePreparedStatement.setString(4,teacher.getTeacherHomePhone().trim())			;
		updatePreparedStatement.setString(5,teacher.getTeacherOfficePhone().trim())		    ;
		updatePreparedStatement.setString(6,teacher.getTeacherMobilePhone().trim())		    ;
		updatePreparedStatement.setString  (7,teacher.getTeacherEmail())					;
		updatePreparedStatement.setString(8,teacher.getTeacherQualifications().trim())	    ;
		updatePreparedStatement.setString(9,teacher.getTeacherDesignation().trim())		    ;
		updatePreparedStatement.setByte  (10,teacher.getTeacherGrade())					    ;
		updatePreparedStatement.setLong   (11,teacher.getTeacherId())			  				;
		System.out.println(updatePreparedStatement);
		int rows = updatePreparedStatement.executeUpdate() 										;
		updatePreparedStatement.close()															;
		return (rows)																		;
	}
	
	// DELETE TEACHER
	public static int deleteTeacher(Teacher teacher) throws SQLException, ClassNotFoundException
	{
		String query = "DELETE FROM teachers WHERE id=?";
		PreparedStatement deletePreparedStatement = Connect.getConnection().prepareStatement(query);
		deletePreparedStatement.setLong(1, teacher.getTeacherId());
		System.out.println(deletePreparedStatement);
		int rows = deletePreparedStatement.executeUpdate() 	;
		deletePreparedStatement.close()						;
		return (rows);
	}

	// VIEW ALL TEACHERS
	public static Result getTeachers() throws SQLException, ClassNotFoundException
	{
		Result result;
		String query = "SELECT * FROM teachers " +
				"INNER JOIN departments ON departments.id = teachers.department_id";
		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = preparedStatement.executeQuery();

		String records[][];
		String subRecords[][];
		int i = 0;

		//make cursor to point to the last row in the ResultSet object
		if (resultSet.last())
		{
			int totalRecords = resultSet.getRow();
			result = new Result(totalRecords);
			System.out.println(totalRecords);
			//make cursor to point to the front of the ResultSet object, just before the first row.
			resultSet.beforeFirst();
			records    = new String[totalRecords][16];
			subRecords = new String[totalRecords][8];
			while (resultSet.next())
			{
				records[i][0] = String.valueOf(resultSet.getLong(1))	    ;
				records[i][1] = String.valueOf(resultSet.getLong(2))	    ;
				records[i][2] = resultSet.getString(3)					;
				records[i][3] = resultSet.getString(4)					;
				records[i][4] = resultSet.getString(5)					;
				records[i][5] = resultSet.getString(6)					;
				records[i][6] = resultSet.getString(7)					;
				records[i][7] = resultSet.getString(8)					;
				records[i][8] = resultSet.getString(9)					;
				records[i][9] = resultSet.getString(10)					;
				records[i][10] = String.valueOf(resultSet.getByte(11))	;
				records[i][11]= String.valueOf(resultSet.getLong(12))	;
				records[i][12]= resultSet.getString(13)					;
				records[i][13]= resultSet.getString(14)					;
				records[i][14]= String.valueOf(resultSet.getLong(15))	;
				records[i][15]= String.valueOf(resultSet.getLong(16))	;
				i++																    ;
			}

			for(int j=0; j<records.length; j++)
			{
				subRecords[j][0] = records[j][0];
				subRecords[j][1] = records[j][12];
				subRecords[j][2] = records[j][2];
				subRecords[j][3] = records[j][3];
				subRecords[j][4] = records[j][6];
				subRecords[j][5] = records[j][7];
				subRecords[j][6] = records[j][8];
				subRecords[j][7] = records[j][9];
			}


	
			result.setRecords(records);
			result.setSubRecords(subRecords);
		}
		else
		{
			result = new Result(0);
		}

		resultSet.close() 			;
		preparedStatement.close()   ;
		return result	 			;
	}

	// Search Teacher
	public static String[] searchTeacher(String teacherName) throws SQLException, ClassNotFoundException
	{
		teacherName = "%" + teacherName + "%" ;
		PreparedStatement searchPreparedStatement = Connect.getConnection().prepareStatement(
				"SELECT * FROM teachers " +
				"INNER JOIN departments ON departments.id = teachers.department_id " +
				"WHERE teacher_name LIKE ?");
		searchPreparedStatement.setString(1, teacherName)			;
		ResultSet resultSet = searchPreparedStatement.executeQuery();
		resultSet.next() ;

		String[] values = new String[16];

		values[0] = String.valueOf(resultSet.getLong(1))	;
		values[1] = String.valueOf(resultSet.getLong(2))					;
		values[2] = resultSet.getString(3)					;
		values[3] = resultSet.getString(4)					;
		values[4] = resultSet.getString(5)					;
		values[5] = resultSet.getString(6)					;
		values[6] = resultSet.getString(7)					;
		values[7] = resultSet.getString(8)					;
		values[8] = resultSet.getString(9)					;
		values[9] = resultSet.getString(10)					;
		values[10] = String.valueOf(resultSet.getByte(11))	;
		values[11]= String.valueOf(resultSet.getLong(12))	;
		values[12]= resultSet.getString(13)					;
		values[13]= resultSet.getString(14)					;
		values[14]= String.valueOf(resultSet.getLong(15))	;
		values[15]= String.valueOf(resultSet.getLong(16))	;


		resultSet.close() ;
		searchPreparedStatement.close();
		return values ;
	}

	
/*
	// Next Teacherid  from sequence  used as a primary key
	protected static int getNextTeacherId() throws SQLException, ClassNotFoundException {
		PreparedStatement preparedStatement1 = Connect.getConnection().prepareStatement("SELECT teacherzseq.NEXTVAL from dual");
		ResultSet resultSet	= preparedStatement1.executeQuery();
		resultSet.next()				;
		int id = resultSet.getInt(1)	;
		resultSet.close()				;
		preparedStatement1.close()		;
		return id 						;
	}

	// to create arry of that size in  String[] getTeachers()
	protected static short getTeachersCount() throws SQLException
	{
		PreparedStatement preparedStatement1 = Connect.conn.prepareStatement("Select count(Name) from teachers");
		ResultSet resultSet				     = preparedStatement1.executeQuery();
		resultSet.next()				;
		short id = resultSet.getShort(1);
		resultSet.close()				;
		preparedStatement1.close()		;
		return id 						;
	}

	// Method that select teacherz from teachers table, and return array of string containing teachers names at the time of subject delete and search.
	protected static String[] getTeachers() throws SQLException
	{
		int      record = 0;
		String[] records   ;
		PreparedStatement preparedStatement1 = Connect.conn.prepareStatement("Select Name from teachers order by Name");
		ResultSet resultSet					 = preparedStatement1.executeQuery();
		records = new String[getTeachersCount()];
		while ( resultSet.next())
		{
			records[record] = resultSet.getString(1);
			record++;
		}
		resultSet.close()			;
		preparedStatement1.close()	;
		return records ;
	}
	// Get TeacherId using his/her Name, usd in creating constructor of MakeTimeTableClass
	protected static int getTeacherId(String tname)throws SQLException
	{
		PreparedStatement preparedStatement1 = Connect.conn.prepareStatement("SELECT Id FROM TEACHERS WHERE Name=?");
		preparedStatement1.setString(1,tname)			  		;
		ResultSet resultSet	= preparedStatement1.executeQuery()	;
		resultSet.next()				;
		int id = resultSet.getInt(1)	;
		resultSet.close()				;
		preparedStatement1.close()		;
		return id 						;
	}


	// Get TeacherId using his/her Name, to be displayed in view time rable form
	protected static String getTeacherName(int teacherId)  throws SQLException
	{
		PreparedStatement preparedStatement1 = Connect.conn.prepareStatement("SELECT Name FROM TEACHERS WHERE Id =?");
		preparedStatement1.setInt(1,teacherId)			  		;
		ResultSet resultSet	= preparedStatement1.executeQuery()	;
		resultSet.next()				;
		String tName = resultSet.getString(1)	;
		resultSet.close()						;
		preparedStatement1.close()				;
		return tName 							;
	}
	*/
}