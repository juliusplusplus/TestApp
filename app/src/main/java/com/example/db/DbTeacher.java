package com.example.db;

import org.litepal.crud.LitePalSupport;

public class DbTeacher extends LitePalSupport {
    private int id;
    private String name;
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

    public String getClasses(){
        return classes;
    }

    public void setClasses(String classes){
        this.classes = classes;
    }

}
