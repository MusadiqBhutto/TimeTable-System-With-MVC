package com.mbhutto.model;

import com.mbhutto.model.Connect;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.TimeTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeTableDataOperations
{
    public static Result subDeptTermSec(Long departmentId, Long termId, long sectionId) throws SQLException, ClassNotFoundException
    {
        Result result;

        String selectQuery = "SELECT * FROM subjects_departments_terms_sections sdts "+
        "INNER JOIN subjects s ON s.id  =  sdts.subject_id "+
        "WHERE sdts.departments_terms_sections_id IN (SELECT id FROM departments_terms_sections WHERE department_id=? AND term_id = ? AND section_id=?)" +
                " AND sdts.is_time_table_set = 0";

        PreparedStatement selectPreparedStatement = Connect.getConnection().prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectPreparedStatement.setLong(1, departmentId)			;
        selectPreparedStatement.setLong(2, termId)			        ;
        selectPreparedStatement.setLong(3, sectionId)			    ;
        System.out.println(selectPreparedStatement.toString())                  ;
        ResultSet resultSet = selectPreparedStatement.executeQuery()            ;

        String records[][];
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][12];
            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1));
                records[i][1] = String.valueOf(resultSet.getLong(2));
                records[i][2] = String.valueOf(resultSet.getLong(3));
                records[i][3] = String.valueOf(resultSet.getLong(4));
                records[i][4] = String.valueOf(resultSet.getLong(5));
                records[i][5] = resultSet.getString(6);
                records[i][6] = resultSet.getString(7);
                records[i][7] = resultSet.getString(8);
                records[i][8] = String.valueOf(resultSet.getInt(9));
                records[i][9] = String.valueOf(resultSet.getInt(10));
                records[i][10] = String.valueOf(resultSet.getInt(11));
                records[i][11] = String.valueOf(resultSet.getInt(12));
                i++;
            }
            result.setRecords(records);
        }
        else
        {
            result = new Result(0);
        }

        resultSet.close();
        selectPreparedStatement.close();
        return result;


    }

    public static Result getFreeClassesForGivenDepartmentTermSection(Long deptTermSecId) throws SQLException, ClassNotFoundException
    {
        Result result;
        String selectQuery = "SELECT	* FROM  time_table "+
                "WHERE allocated = 0 AND type IS NULL AND departments_terms_sections_id = ? ";

        PreparedStatement selectPreparedStatement = Connect.getConnection().prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectPreparedStatement.setLong(1, deptTermSecId);
        System.out.println(selectPreparedStatement.toString());
        ResultSet resultSet = selectPreparedStatement.executeQuery();
        String[][] records;
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][7];

            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1));
                records[i][1] = String.valueOf(resultSet.getLong(2));
                records[i][2] = String.valueOf(resultSet.getLong(3));
                records[i][3] = String.valueOf(resultSet.getByte(4));
                records[i][4] = String.valueOf(resultSet.getByte(5));
                records[i][5] = resultSet.getString(6);
                records[i][6] = String.valueOf(resultSet.getByte(7));
                i++;
            }

            result.setRecords(records);
        }
        else
        {
            System.out.println("ELSE");
            result = new Result(0);
        }

        resultSet.close();
        selectPreparedStatement.close();
        return result;
    }

    public static Result getTimeTable(Long departmentId, Long termId, Long sectionId) throws SQLException, ClassNotFoundException
    {
        Result result;
        String selectQuery = "SELECT tt.id, tt.day, tt.class_no, tt.allocated, tt.type, " +
                " s.subject_code, s.subject_name, s.subject_short_name," +
                " t.teacher_name " +
                " FROM  time_table tt " +
                " INNER JOIN departments_terms_sections dts ON dts.id = tt.departments_terms_sections_id" +
                " LEFT JOIN subjects_departments_terms_sections sdts ON sdts.id = tt.subjects_departments_terms_sections_id" +
                " LEFT JOIN subjects s ON s.id = sdts.subject_id" +
                " LEFT JOIN teachers_time_table ttt ON ttt.time_table_id = tt.id" +
                " LEFT JOIN teachers t ON t.id = ttt.teacher_id" +
                " WHERE dts.department_id = ? AND dts.term_id = ? AND dts.section_id = ?" +
                " ORDER BY tt.class_no, tt.day ";

        PreparedStatement selectTimeTablePreparedStatement = Connect.getConnection().prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectTimeTablePreparedStatement.setLong(1, departmentId);
        selectTimeTablePreparedStatement.setLong(2, termId);
        selectTimeTablePreparedStatement.setLong(3, sectionId);
        System.out.println(selectTimeTablePreparedStatement.toString());
        ResultSet resultSet = selectTimeTablePreparedStatement.executeQuery();
        String[][] records;
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][9];

            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1));
                records[i][1] = String.valueOf(resultSet.getInt(2));
                records[i][2] = String.valueOf(resultSet.getInt(3));
                records[i][3] = String.valueOf(resultSet.getInt(4));
                records[i][4] = resultSet.getString(5);
                records[i][5] = resultSet.getString(6);
                records[i][6] = resultSet.getString(7);
                records[i][7] = resultSet.getString(8);
                records[i][8] = resultSet.getString(9);
                if(records[i][4] != null && records[i][4].equals("Practical")) records[i][7] = records[i][7] + " (Pr)";
                i++;
            }

            result.setRecords(records);
        }
        else
        {
            result = new Result(0);
        }

        resultSet.close();
        selectTimeTablePreparedStatement.close();
        return result;
    }

    public static Result getDistinctSubjectsTimeTable(long departmentId, long termId, long sectionId) throws SQLException, ClassNotFoundException
    {
        Result result;
        String selectQuery = "SELECT DISTINCT s.subject_code, s.subject_name, s.subject_short_name, s.subject_theory_classes_in_week, s.subject_practical_classes_in_week, "+
        " t.teacher_name FROM  time_table tt "+
        " INNER JOIN departments_terms_sections dts ON dts.id = tt.departments_terms_sections_id "+
        " LEFT JOIN subjects_departments_terms_sections sdts ON sdts.id = tt.subjects_departments_terms_sections_id "+
        " LEFT JOIN subjects s ON s.id = sdts.subject_id "+
        " LEFT JOIN teachers_time_table ttt ON ttt.time_table_id = tt.id "+
        " LEFT JOIN teachers t ON t.id = ttt.teacher_id "+
        " WHERE dts.department_id = ? AND dts.term_id = ? AND dts.section_id = ? ";

        PreparedStatement selectTimeTablePreparedStatement = Connect.getConnection().prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectTimeTablePreparedStatement.setLong(1, departmentId);
        selectTimeTablePreparedStatement.setLong(2, termId);
        selectTimeTablePreparedStatement.setLong(3, sectionId);
        System.out.println(selectTimeTablePreparedStatement.toString());
        ResultSet resultSet = selectTimeTablePreparedStatement.executeQuery();
        String[][] records;
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][6];

            while (resultSet.next())
            {
                if(resultSet.getString(1) == null) continue;
                records[i][0] = resultSet.getString(1);
                records[i][1] = resultSet.getString(2);
                records[i][2] = resultSet.getString(3);
                records[i][3] = String.valueOf(resultSet.getInt(4));
                records[i][4] = String.valueOf(resultSet.getInt(5));
                records[i][5] = resultSet.getString(6);
                i++;
            }

            result.setRecords(records);
        }
        else
        {
            System.out.println("ELSE");
            result = new Result(0);
        }

        resultSet.close();
        selectTimeTablePreparedStatement.close();
        return result;
    }

    public static Result deptTeachers(Long deptID) throws SQLException, ClassNotFoundException
    {
        Result result;
        String selectQuery = "SELECT * FROM teachers WHERE department_id = ?";
        PreparedStatement selectPreparedStatement = Connect.getConnection().prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectPreparedStatement.setLong(1, deptID );
        System.out.println(selectPreparedStatement.toString());
        ResultSet resultSet = selectPreparedStatement.executeQuery();

        String records[][];
        int i = 0;

        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            resultSet.beforeFirst();
            records = new String[totalRecords][11];

            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1));
                records[i][1] = String.valueOf(resultSet.getLong(2));
                records[i][2] = resultSet.getString(3);
                records[i][3] = resultSet.getString(4);
                records[i][4] = resultSet.getString(5);
                records[i][5] = resultSet.getString(6);
                records[i][6] = resultSet.getString(7);
                records[i][7] = resultSet.getString(8);
                records[i][8] = resultSet.getString(9);
                records[i][9] = resultSet.getString(10);
                records[i][10] = String.valueOf(resultSet.getByte(11));
                i++;
            }
            result.setRecords(records);
        }

        else
        {
            result = new Result(0);
        }

        resultSet.close();
        selectPreparedStatement.close();
        return result;

    }

    public static int createTimeTable(TimeTable timeTable) throws SQLException, ClassNotFoundException {
        String updateQuery = "UPDATE time_table SET " +
                "subjects_departments_terms_sections_id = ?, " +
                "type = ?, " +
                "allocated = ? " +
                "WHERE id = ?" ;

        PreparedStatement updatePreparedStatement = Connect.getConnection().prepareStatement(updateQuery) 	;
        updatePreparedStatement.setLong(1,timeTable.getSubjectDepartmentTermSectionId())   ;
        updatePreparedStatement.setString(2,timeTable.getTimeTableClassType())              ;
        updatePreparedStatement.setByte(3,timeTable.getAllocated())                         ;
        updatePreparedStatement.setLong(4,timeTable.getTimeTableId())						;
        System.out.println(updatePreparedStatement.toString())							            	;
        int rows = updatePreparedStatement.executeUpdate() 									            ;
        updatePreparedStatement.close()														            ;

        if(rows > 0)
        {
            String insertQuery = "INSERT into teachers_time_table(teacher_id, time_table_id) VALUES (?, ?)" ;
            PreparedStatement createPreparedStatement = Connect.getConnection().prepareStatement(insertQuery) 	;
            createPreparedStatement.setLong(1,timeTable.getTeacherOne().getTeacherId())   ;
            createPreparedStatement.setLong(2,timeTable.getTimeTableId())   ;
            int insert = createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
        }

        if(timeTable.getIsTimeTableSetForSubject() == 1)
        {
            String updateSDTSQuery = "UPDATE subjects_departments_terms_sections " +
                    "SET subjects_departments_terms_sections.is_time_table_set = 1 " +
                    "WHERE id = ? AND subject_id = ?" ;

            PreparedStatement updateSDTSPreparedStatement = Connect.getConnection().prepareStatement(updateSDTSQuery) 	  ;
            updateSDTSPreparedStatement.setLong(1,timeTable.getSubjectDepartmentTermSectionId())             ;
            updateSDTSPreparedStatement.setLong(2,timeTable.getSubject().getSubjectId())                      ;
            System.out.println(updateSDTSPreparedStatement.toString())							            	          ;
            updateSDTSPreparedStatement.executeUpdate() 									                      ;
            updateSDTSPreparedStatement.close()														                     ;
        }

        if(timeTable.getIsTimeTableSetForDeptTermSection() == 1)
        {
            String updateSDTSQuery = "UPDATE departments_terms_sections SET is_time_table_set = 1 WHERE id = ?" ;

            PreparedStatement updateSDTSPreparedStatement = Connect.getConnection().prepareStatement(updateSDTSQuery) 	  ;
            updateSDTSPreparedStatement.setLong(1,timeTable.getDepartmentTermSectionId())             ;
            updateSDTSPreparedStatement.executeUpdate() 									                      ;
            updateSDTSPreparedStatement.close()														                     ;
        }

        return rows;
    }

    public static Result getDistinctDepartmentsForTimeTable() throws SQLException, ClassNotFoundException
    {
        Result result ;
        String selectQuery = "SELECT DISTINCT d.id, d.department_name, d.department_short_name, d.department_terms, d.department_sections FROM departments d " +
                "INNER JOIN departments_terms_sections dts ON d.id = dts.department_id " +
                "WHERE dts.is_time_table_set = 0";

        PreparedStatement selectPreparedStatement = Connect.getConnection().prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = selectPreparedStatement.executeQuery();

        String records[][];
        int i = 0;

        //make cursor to point to the last row in the ResultSet object
        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            result = new Result(totalRecords);
            System.out.println(totalRecords);
            //make cursor to point to the front of the ResultSet object, just before the first row.
            resultSet.beforeFirst();
            records = new String[totalRecords][5];
            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1))					;
                records[i][1] = resultSet.getString(2)								;
                records[i][2] = resultSet.getString(3)								;
                records[i][3] = String.valueOf(resultSet.getInt(4))					;
                records[i][4] = String.valueOf(resultSet.getInt(5))					;
                i++																				;
            }
            result.setRecords(records);
        }
        else
        {
            result = new Result(0);
        }

        resultSet.close() 			;
        selectPreparedStatement.close()   ;
        return result	 			;
    }

    public static Result getDepartmentTermsForTimeTable(long selectedDepartmentId) throws SQLException, ClassNotFoundException {
        Result departmentTermsResult       ;
        String departmentTermsSelectQuery = "SELECT DISTINCT t.id, t.term FROM terms t " +
                "INNER JOIN departments_terms_sections dts ON t.id = dts.term_id " +
                "WHERE dts.department_id = ? AND dts.is_time_table_set = 0";

        PreparedStatement selectDepartmentsTermsPreparedStatement = Connect.getConnection().prepareStatement(departmentTermsSelectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectDepartmentsTermsPreparedStatement.setLong(1, selectedDepartmentId)             ;
        ResultSet resultSet = selectDepartmentsTermsPreparedStatement.executeQuery();

        String records[][];
        int i = 0;

        //make cursor to point to the last row in the ResultSet object
        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            departmentTermsResult = new Result(totalRecords);
            System.out.println(totalRecords);
            //make cursor to point to the front of the ResultSet object, just before the first row.
            resultSet.beforeFirst();
            records = new String[totalRecords][2];
            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1))					;
                records[i][1] = String.valueOf(resultSet.getLong(2))	                ;
                i++																				;
            }
            departmentTermsResult.setRecords(records);
        }
        else
        {
            departmentTermsResult = new Result(0);
        }

        resultSet.close() 			;
        selectDepartmentsTermsPreparedStatement.close()   ;
        return departmentTermsResult	 			      ;
    }

    public static Result getDepartmentTermsSectionsForTimeTable(long selectedDepartmentId, long selectedTermId) throws SQLException, ClassNotFoundException {
        Result departmentTermsSectionsResult       ;
        String departmentTermsSectionSelectQuery = "SELECT DISTINCT s.id, s.section FROM sections s " +
                "INNER JOIN departments_terms_sections dts ON s.id = dts.section_id " +
                "WHERE dts.department_id = ? AND dts.term_id = ? AND dts.is_time_table_set = 0";

        PreparedStatement selectDepartmentsTermsSectionsPreparedStatement = Connect.getConnection().prepareStatement(departmentTermsSectionSelectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        selectDepartmentsTermsSectionsPreparedStatement.setLong(1, selectedDepartmentId)             ;
        selectDepartmentsTermsSectionsPreparedStatement.setLong(2, selectedTermId)                   ;
        ResultSet resultSet = selectDepartmentsTermsSectionsPreparedStatement.executeQuery();

        String records[][];
        int i = 0;

        //make cursor to point to the last row in the ResultSet object
        if (resultSet.last())
        {
            int totalRecords = resultSet.getRow();
            departmentTermsSectionsResult = new Result(totalRecords);
            System.out.println(totalRecords);
            //make cursor to point to the front of the ResultSet object, just before the first row.
            resultSet.beforeFirst();
            records = new String[totalRecords][2];
            while (resultSet.next())
            {
                records[i][0] = String.valueOf(resultSet.getLong(1))					;
                records[i][1] = resultSet.getString(2)	                            ;
                i++																				;
            }
            System.out.println("*****" + i);
            departmentTermsSectionsResult.setRecords(records);
        }
        else
        {
            departmentTermsSectionsResult = new Result(0);
        }

        resultSet.close() 			;
        selectDepartmentsTermsSectionsPreparedStatement.close()   ;
        return departmentTermsSectionsResult	 			      ;
    }
}
