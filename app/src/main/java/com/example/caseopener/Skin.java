package com.example.caseopener;

import java.util.HashMap;


// In your Skin.java file
public class Skin {





    public String id;
    public String name;
    public String rarity;
    public String wear;
    public boolean stattrak;
    public String image;
    public String price;


    // Your existing constructor
    public Skin( String id, String name, String rarity, String wear, boolean stattrak, String image, String price) {
        this.name = name;
        this.rarity = rarity;
        this.wear = wear;
        this.id = id;
        this.stattrak = stattrak;
        this.image = image;
        this.price = price;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getRarity() {
        return rarity;
    }
    public String getWear() { return wear; }
    public String getId(){return id;}
    public String getImage(){return image;}
    public boolean getStattrak(){return stattrak;}
    public String getPrice(){return price;}









}
