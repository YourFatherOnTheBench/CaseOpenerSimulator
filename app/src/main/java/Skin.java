public class Skin {
    private String name;
    private String rarity;
    private String wear;
    public void SkinInfo(String name, String rarity, String wear) {
        this.name = name;
        this.rarity = rarity;
        this.wear = wear;
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
