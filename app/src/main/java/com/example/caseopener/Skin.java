package com.example.caseopener;
// In your Skin.java file
public class Skin {
    private String name;
    private String rarity;
    private String wear;
    private String backgroundColor; // Add this field

    // Your existing constructor
    public Skin(String name, String rarity, String wear) {
        this.name = name;
        this.rarity = rarity;
        this.wear = wear;
        this.backgroundColor = "#808080"; // Default color
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getRarity() { return rarity; }
    public String getWear() { return wear; }

    public String getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(String color) { this.backgroundColor = color; }
}
