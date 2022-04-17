package com.mbhutto.entity;

import com.mbhutto.model.TimeTableDataOperations;

import java.sql.SQLException;

public class SubjectDepartmentTermSection
{
    private long SubjectDepartmentTermSectionId ;
    private long departmentId;
    private long termId ;
    private long sectionId ;
    private long subjectId ;

    public SubjectDepartmentTermSection(long departmentId, long termId, long sectionId)
    {
        this.departmentId = departmentId;
        this.termId = termId;
        this.sectionId = sectionId;
    }

    public SubjectDepartmentTermSection(long departmentId)
    {
        this.departmentId = departmentId;
    }

    public SubjectDepartmentTermSection()
    {

    }

    public long getSubjectDepartmentTermSectionId()
    {
        return SubjectDepartmentTermSectionId;
    }

    public void setSubjectDepartmentTermSectionId(long subjectDepartmentTermSectionId)
    {
        SubjectDepartmentTermSectionId = subjectDepartmentTermSectionId;
    }

    public long getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(long departmentId)
    {
        this.departmentId = departmentId;
    }

    public long getTermId()
    {
        return termId;
    }

    public void setTermId(long termId)
    {
        this.termId = termId;
    }

    public long getSectionId()
    {
        return sectionId;
    }

    public void setSectionId(long sectionId)
    {
        this.sectionId = sectionId;
    }

    public long getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(long subjectId)
    {
        this.subjectId = subjectId;
    }




    public Result getSubDeptTermSec() throws SQLException, ClassNotFoundException
    {
        return TimeTableDataOperations.subDeptTermSec(this.departmentId, this.termId, this.sectionId);
    }

    public Result getDeptTeachers() throws SQLException, ClassNotFoundException
    {
        return TimeTableDataOperations.deptTeachers(this.departmentId);
    }





}
