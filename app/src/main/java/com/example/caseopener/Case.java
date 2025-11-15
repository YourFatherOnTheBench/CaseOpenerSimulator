package com.example.caseopener;
import java.util.ArrayList;


public class Case {

    public String id;
    public String name;
    public String imgName;
    public ArrayList<String> skins;

    public Case(String id, String name, String imgPath) {
        this.id = id;
        this.name = name;
        this.imgName = imgPath;
        this.skins = new ArrayList<>();
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

    public ArrayList<String> getSkins() {
        return skins;

    }
    public void setSkins(ArrayList<String> skins) {
        this.skins = skins;
    }
}
