package com.example.caseopener;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Skin get(String id)
    {
        return skins_database.get(id);
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

            for(int i = 0; i < arr.length(); i++)
            {
                JSONObject obj = arr.getJSONObject(i);

                Skin skin = new Skin(
                        obj.optString("id"),
                        obj.optString("name"),
                        obj.optString("rarity"),
                        ("Factory-new"),
                        obj.optBoolean("stattrak"),
                        obj.optString("image"),
                        ("9999")
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



