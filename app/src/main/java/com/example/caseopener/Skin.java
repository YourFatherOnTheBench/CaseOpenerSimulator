package com.example.caseopener;
// In your Skin.java file
public class Skin {
    public String id;
    public String name;
    public String rarity;
    public String wear;
    public boolean stattrak;
    public String image;


    // Your existing constructor
    public Skin( String id, String name, String rarity, String wear, boolean stattrak, String image) {
        this.name = name;
        this.rarity = rarity;
        this.wear = wear;
        this.id = id;
        this.stattrak = stattrak;
        this.image = image;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getRarity() { return rarity; }
    public String getWear() { return wear; }
    public String getId(){return id;}
    public Skin GetData(String id)
    {
        return Skin.get(id);
    }

}
