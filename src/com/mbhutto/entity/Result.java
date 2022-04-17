package com.mbhutto.entity;

public class Result
{
     public String[][] records     ;
     String[][] subRecords  ;
     public int length             ;

    public Result(int length)
    {
        this.records = records;
        this.length = length;
    }

    public String[][] getRecords()
    {
        return records;
    }

    public void setRecords(String[][] records)
    {
        this.records = records;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String[][] getSubRecords() {
        return subRecords;
    }

    public void setSubRecords(String[][] subRecords) {
        this.subRecords = subRecords;
    }
}
