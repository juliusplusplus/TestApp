package com.example.entity;

import java.io.Serializable;

public class Record implements Serializable {
    public Record(String name, String score){
        this.name=name;
        this.score=score;
    }
    public Record(String name, String score, String temp){
        this.name=name;
        this.score=score;
        this.temp=temp;
    }
    private String name;
    private String score;
    private String temp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
