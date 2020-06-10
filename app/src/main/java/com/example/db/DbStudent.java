package com.example.db;

public class DbStudent {
    private int id;
    private String name;
    private String sex;
    private String classes;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }

    public String getClasses(){
        return classes;
    }

    public void setClasses(String classes){
        this.classes = classes;
    }
}
