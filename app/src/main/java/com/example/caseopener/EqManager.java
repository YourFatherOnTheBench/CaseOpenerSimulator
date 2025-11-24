package com.example.caseopener;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class EqManager {

    private static EqManager instance;
    private static final String FILENAME = "data.json";

    // I corrected the typo: 'AcuairedSkins' -> 'acquiredSkins'
    public ArrayList<String> acquiredSkins = new ArrayList<>();

    public static EqManager getInstance() {
        if (instance == null) {
            instance = new EqManager();
        }
        return instance;
    }
    public void saveSkins(Context context) {
        JSONArray jsonArray = new JSONArray();

        try {

            for (String skinId : acquiredSkins) {
                JSONObject skinObj = new JSONObject();
                skinObj.put("id", skinId);
                jsonArray.put(skinObj);
            }

            String jsonString = jsonArray.toString();
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSkins(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }


            if (sb.length() == 0) return;


            acquiredSkins.clear();


            JSONArray jsonArray = new JSONArray(sb.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String id = obj.getString("id");
                acquiredSkins.add(id);
            }

            fis.close();

        } catch (IOException | JSONException e) {
            System.out.println("No inventory file found, starting new.");
        }
    }

    public void addSkin(Context context, String skinId) {
        acquiredSkins.add(skinId);
        saveSkins(context);
    }

    public Double GetMoenyInt(Context context)
    {
        Double SumOfPrices = 0.0;
        for(int i = 0; i < acquiredSkins.size(); i++)
        {
            String id = acquiredSkins.get(i);
            Skin CurrentSkin = SkinManager.getInstance().skins_database.get(id);
            String Price = CurrentSkin.getPrice();
            Double PriceDouble = Double.parseDouble(Price);
            SumOfPrices += PriceDouble;



        }
        return SumOfPrices;
    }
    public String GetMoenyString(Context context)
    {
        Double SumOfPrices = 0.0;
        for(int i = 0; i < acquiredSkins.size(); i++)
        {
            String id = acquiredSkins.get(i);
            Skin CurrentSkin = SkinManager.getInstance().skins_database.get(id);
            String Price = CurrentSkin.getPrice();
            Double PriceDouble = Double.parseDouble(Price);
            SumOfPrices += PriceDouble;



        }
        return String.format("%.2f$", SumOfPrices);

    }
    public void removeSkin(Context context, String skinId) {
        acquiredSkins.remove(skinId);
        saveSkins(context);
    }






}