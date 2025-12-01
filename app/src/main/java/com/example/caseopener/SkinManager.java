package com.example.caseopener;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.Locale;

public class SkinManager {
    private static SkinManager instance;
    public ArrayList<Skin> skinList = new ArrayList<>();


    public HashMap<String, Skin> skins_database = new HashMap<>();
    public static SkinManager getInstance()
    {
        if(instance == null)
        {
            instance = new SkinManager();
        }
        return instance;

    }


    public void register(Skin skin) {
        skins_database.put(skin.getId(), skin);

    }


    static String [] AllSkinRarities = {
            "Consumer Grade",
            "Mil-Spec Grade",
            "Restricted",
            "Classified",
            "Covert",
            "Extraordinary"
    };

    public Skin get(String id)
    {
        return skins_database.get(id);
    }

    static String RandomSkin_Generator(int Case_position)
    {
        Random rand = new Random();

        String Skin_Rarity = "";
        String rarity = "";
        double rarityROLL = Math.random() * 100;
        if(rarityROLL < 60.0) {
            rarity = "Consumer Grade";
        } else if(rarityROLL < 80) {
            rarity = "Mil-Spec Grade";
        } else if(rarityROLL < 96.0){
            rarity = "Restricted";
        } else if(rarityROLL < 99.0){
            rarity = "Classified";
        } else if(rarityROLL < 99.7){
            rarity = "Covert";
        } else {
            rarity = "Extraordinary";
        }
        if (Case_position == 4)
        {
            rarity = "Covert";
        }
        String skin = "";
        do {
            int SkinID = rand.nextInt(CaseManager.getInstance().cases.get(Case_position).skins.size());
            skin = CaseManager.getInstance().cases.get(Case_position).skins.get(SkinID);
            Skin RandomSkin = SkinManager.getInstance().skins_database.get(skin);
            try {
                JSONObject rarityJson = new JSONObject(RandomSkin.getRarity());
                String rarityName = rarityJson.getString("name");

                Skin_Rarity = rarityName;
            } catch (JSONException e) {
                Skin_Rarity = RandomSkin.getRarity(); // Fallback
                e.printStackTrace();
            }

            Log.d("Skin",SkinManager.getInstance().skins_database.get(skin).name);
            Log.d("SkinRarity",SkinManager.getInstance().skins_database.get(skin).rarity);
            Log.d("SkinRaritygetter",Skin_Rarity);
            Log.d("Rolled rarity",rarity);
        } while (!rarity.equals(Skin_Rarity));
                return skin;
    }
    public void ReadFromJSON(Context context, String filename)
    {

        try
        {
            InputStream inputStream = context.getAssets().open(filename);

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            String jsonStr = new String(buffer, StandardCharsets.UTF_8);

            JSONArray arr = new JSONArray(jsonStr);

            skinList.clear();




            Random random = new Random();

            for(int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);





                // Tworzenie skina z wyliczoną ceną
                Skin skin = new Skin(
                        obj.optString("id"),
                        obj.optString("name"),
                        obj.optString("rarity"),
                        "Factory New",
                        obj.optBoolean("stattrak"),
                        obj.optString("image"),
                        obj.optString("price")
                );

                register(skin);
                skinList.add(skin);
            }




            System.out.println("Loaded " + skinList.size() + " skins");


        } catch (Exception e) {
            e.printStackTrace();
        }





    }

}



