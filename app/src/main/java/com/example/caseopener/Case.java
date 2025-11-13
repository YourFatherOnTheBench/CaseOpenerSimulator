package com.example.caseopener;

public class Case {

    private String name;
    private String imgName;

    public Case(String name, String imgPath) {
        this.name = name;
        this.imgName = imgPath;
    }

    public String getName() {
        return name;
    }

    public String getImgName() {
        return imgName;
    }

}
