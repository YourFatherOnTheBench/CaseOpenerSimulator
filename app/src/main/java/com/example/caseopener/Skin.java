package com.example.caseopener;

public class Skin {
    private String imgName;
    private String name;
    private String rarity;
    private String wear;

    public Skin(String imgName, String name, String rarity, String wear) {

        this.imgName = imgName;
        this.name = name;
        this.rarity = rarity;
        this.wear = wear;
    }

    public String getImgName() {
        return imgName;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getWear() {
        return wear;
    }
}
