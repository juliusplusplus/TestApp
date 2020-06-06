package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exam implements Serializable {
    private String name;
    private List<Record> records;

    public Exam(String name) {
        this.name = name;
        this.records = new ArrayList<Record>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public void addRecords(Record record) {
        records.add(record);
    }
    public void clearAllRecords() {
        records.clear();
    }
    public String searchRecord(String stu_name) {
        String result = null;
        for (Record record : records) {
            if (record.getName() == stu_name) {
                return record.getScore();
            }
        }
        return null;
    }
}
