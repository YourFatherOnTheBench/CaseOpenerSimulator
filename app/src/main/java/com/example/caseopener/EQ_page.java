package com.example.caseopener;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.Inflater;

public class EQ_page extends AppCompatActivity {




    ArrayList<String> Eq_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq_page);


        GridLayout gridLayout = findViewById(R.id.SkinBlockEQ);
        LayoutInflater inflater = LayoutInflater.from(this);


        Button menuButton = findViewById(R.id.Back_to_menu_btn);

        GetDataFromJSON(this, "data.json");

        for(int i = 0; i < Eq_list.size(); i++) {

            Log.d("EQ",Eq_list.get(i));



            View Skin_block = inflater.inflate(R.layout.block, gridLayout, false );
            String id = Eq_list.get(i);
            Skin currentSkin = SkinManager.getInstance().skins_database.get(id);

            Log.d("CURRENT SKIN", currentSkin.toString());
            Log.d("CURRENT SKIN: NAME", currentSkin.getName());
            Log.d("CURRENT SKIN: Rarity", currentSkin.getRarity());
            Log.d("CURRENT SKIN: IMAGE", currentSkin.getImage());




            LinearLayout skinBackground = Skin_block.findViewById(R.id.skin);
            if (currentSkin != null) {
                TextView name = Skin_block.findViewById(R.id.nameSkin);
                TextView rarity = Skin_block.findViewById(R.id.raritySkin);
                TextView price = Skin_block.findViewById(R.id.Price);
                ImageView skinImage = Skin_block.findViewById(R.id.imageSkin);

                name.setText(currentSkin.getName());
                price.setText("9999.99$");
                Glide.with(this).load(currentSkin.getImage()).into(skinImage);

                try {
                    JSONObject rarityJson = new JSONObject(currentSkin.getRarity());
                    String rarityName = rarityJson.getString("name");
                    String colorName = rarityJson.getString("color").toUpperCase();

                    rarity.setText(rarityName);
                    skinBackground.setBackgroundColor(Color.parseColor(colorName)); // Use Color.parseColor()
                } catch (JSONException e) {
                    rarity.setText(currentSkin.getRarity()); // Fallback
                    e.printStackTrace();
                }
            }
            gridLayout.addView(Skin_block);


        }




        menuButton.setOnClickListener(v -> {
            finish();
        });







    }


    public void GetDataFromJSON(Context context, String filename)
    {
        try{
            InputStream inputStream = context.getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray arr = new JSONArray(json);
            Eq_list.clear();
            for(int i = 0; i < arr.length(); i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                Eq_list.add(obj.optString("id"));





            }


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}