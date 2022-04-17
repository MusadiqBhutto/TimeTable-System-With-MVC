package com.mbhutto.entity;

import com.mbhutto.model.TimeTableDataOperations;

import java.sql.SQLException;

public class TimeTable
{
    private long timeTableId                    ;
    private long departmentTermSectionId        ;
    private long subjectDepartmentTermSectionId ;
    private int timeTableDay                    ;
    private int timeTableClassNo                ;
    private String timeTableClassType           ;
    private byte allocated                      ;
    private Teacher teacherOne                  ;
    private Teacher teacherTwo                  ;
    private Subject subject                     ;
    private byte isTimeTableSetForSubject       ;
    private byte isTimeTableSetForDeptTermSection;
    private SubjectDepartmentTermSection subjectDepartmentTermSection ;


    public TimeTable()
    {

    }

    public TimeTable(long departmentTermSectionId) 
    {
        this.departmentTermSectionId = departmentTermSectionId;
    }

    public TimeTable(long timeTableId, long departmentTermSectionId, long subjectDepartmentTermSectionId, int timeTableDay, int timeTableClassNo, String timeTableClassType, byte allocated) 
    {
        this.timeTableId = timeTableId;
        this.departmentTermSectionId = departmentTermSectionId;
        this.subjectDepartmentTermSectionId = subjectDepartmentTermSectionId;
        this.timeTableDay = timeTableDay;
        this.timeTableClassNo = timeTableClassNo;
        this.timeTableClassType = timeTableClassType;
        this.allocated = allocated;
    }

    public long getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(long timeTableId) {
        this.timeTableId = timeTableId;
    }

    public long getDepartmentTermSectionId() {
        return departmentTermSectionId;
    }

    public void setDepartmentTermSectionId(long departmentTermSectionId) {
        this.departmentTermSectionId = departmentTermSectionId;
    }

    public long getSubjectDepartmentTermSectionId() {
        return subjectDepartmentTermSectionId;
    }

    public void setSubjectDepartmentTermSectionId(long subjectDepartmentTermSectionId) {
        this.subjectDepartmentTermSectionId = subjectDepartmentTermSectionId;
    }

    public int getTimeTableDay() {
        return timeTableDay;
    }

    public void setTimeTableDay(int timeTableDay) {
        this.timeTableDay = timeTableDay;
    }

    public int getTimeTableClassNo() {
        return timeTableClassNo;
    }

    public void setTimeTableClassNo(int timeTableClassNo) {
        this.timeTableClassNo = timeTableClassNo;
    }

    public String getTimeTableClassType() {
        return timeTableClassType;
    }

    public void setTimeTableClassType(String timeTableClassType) {
        this.timeTableClassType = timeTableClassType;
    }

    public byte getAllocated() {
        return allocated;
    }

    public void setAllocated(byte allocated) {
        this.allocated = allocated;
    }

    public Teacher getTeacherOne() {
        return teacherOne;
    }

    public void setTeacherOne(Teacher teacherOne) {
        this.teacherOne = teacherOne;
    }

    public Teacher getTeacherTwo() {
        return teacherTwo;
    }

    public void setTeacherTwo(Teacher teacherTwo) {
        this.teacherTwo = teacherTwo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectDepartmentTermSection getSubjectDepartmentTermSection() {
        return subjectDepartmentTermSection;
    }

    public void setSubjectDepartmentTermSection(SubjectDepartmentTermSection subjectDepartmentTermSection) {
        this.subjectDepartmentTermSection = subjectDepartmentTermSection;
    }

    public byte getIsTimeTableSetForSubject() {
        return isTimeTableSetForSubject;
    }

    public void setIsTimeTableSetForSubject(byte isTimeTableSetForSubject) {
        this.isTimeTableSetForSubject = isTimeTableSetForSubject;
    }

    public byte getIsTimeTableSetForDeptTermSection() {
        return isTimeTableSetForDeptTermSection;
    }

    public void setIsTimeTableSetForDeptTermSection(byte isTimeTableSetForDeptTermSection) {
        this.isTimeTableSetForDeptTermSection = isTimeTableSetForDeptTermSection;
    }
    
    public int createTimeTable() throws SQLException, ClassNotFoundException {
        return TimeTableDataOperations.createTimeTable(this);

    }

   
}
