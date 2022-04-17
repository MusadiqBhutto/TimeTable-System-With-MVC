package com.mbhutto.entity;

import com.mbhutto.model.SubjectDataOperations;

import java.sql.SQLException;

public class DepartmentTermSubject {
    long departmentId;
    long termId ;
    long subjectId ;

    public DepartmentTermSubject()
    {

    }

    public DepartmentTermSubject(long departmentId, int termId) {
        this.departmentId = departmentId;
        this.termId = termId;
    }

    public DepartmentTermSubject(long departmentId, int termId, long subjectId) {
        this.departmentId = departmentId;
        this.termId = termId;
        this.subjectId = subjectId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public void addRecord() throws SQLException, ClassNotFoundException
    {
        SubjectDataOperations.deptSubTerm(this.departmentId,  this.termId, this.subjectId );
    }

    public String toString()
    {
        return "DepartmentTermSubject{" +
                "departmentId=" + departmentId +
                ", termId=" + termId +
                ", subjectId=" + subjectId +
                '}';
    }
}
