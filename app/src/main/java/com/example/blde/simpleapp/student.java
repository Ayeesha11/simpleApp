package com.example.blde.simpleapp;

/**
 * Created by BLDE on 7/28/2018.
 */

public class student {
    int image;
    String Name;
    String DOB;

    public student(int image, String name, String DOB) {
        this.image = image;
        this.Name = name;
        this.DOB = DOB;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }


    }
