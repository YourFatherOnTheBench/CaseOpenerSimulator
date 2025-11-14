package com.example.caseopener;

public class Case {

    public String id;
    public String name;
    public String imgName;
    public String[] skins;

    public Case(String name, String imgPath) {
        //this.id = id;
        this.name = name;
        this.imgName = imgPath;
        //this.skins = skins;
    }

    public String getName() {
        return name;
    }

    public String getImgName() {
        return imgName;
    }

    public String getId() {
        return id;
    }

    public String[] getSkins() {
        return skins;

    }
}
