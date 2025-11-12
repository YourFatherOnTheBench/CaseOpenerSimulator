package com.example.caseopener;

public class Case {

    private String name;
    private String imgPath;

    public Case(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public String getImgPath() {
        return imgPath;
    }

}
