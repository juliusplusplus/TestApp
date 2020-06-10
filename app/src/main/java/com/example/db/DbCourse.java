package com.example.db;

public class DbCourse {
    private int id;
    private String sex;
    private String project;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }

    public String getProject(){
        return project;
    }

    public void setProject(String project){
        this.project = project;
    }

}
