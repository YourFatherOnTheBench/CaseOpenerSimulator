package com.example.caseopener;

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
import androidx.core.view.ViewKt;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contract extends AppCompatActivity {


    ArrayList<String> Eq_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contract);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridLayout gridLayout = findViewById(R.id.SkinBlockEQ);
        LayoutInflater inflater = LayoutInflater.from(this);


        Button Deposit = findViewById(R.id.CurrentDeposit);
        Deposit.setText(EqManager.getInstance().GetMoneyString(this));

        Log.d("EQ", EqManager.getInstance().acquiredSkins.toString());



        Button menuButton = findViewById(R.id.Back_to_menu_btn);



        for(int i = 0; i < EqManager.getInstance().acquiredSkins.size(); i++) {

            Log.d("EQ",EqManager.getInstance().acquiredSkins.get(i));



            View Skin_block = inflater.inflate(R.layout.block, gridLayout, false );

            String id = EqManager.getInstance().acquiredSkins.get(i);
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
                price.setText(currentSkin.getPrice() + "$");
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

            //Klikiecie na skiny dodaje je do kontraktu
            Skin_block.setId(i);
            Map<Integer, String> Skins_in_Contract = new HashMap<>();
            Skin_block.setOnClickListener(v -> {
                skinBackground.setBackgroundColor(Color.parseColor("#07f01e"));
                int SkinID = Skin_block.getId();
                String ID = String.valueOf(SkinID);
                EqManager.getInstance().acquiredSkins.get(SkinID);
                Log.d("SkinID",currentSkin.toString());
                Log.d("BLOCKID",ID );
            });



            gridLayout.addView(Skin_block);


        }




        menuButton.setOnClickListener(v -> {
            finish();
        });
    }
    public void AddSkin_ToCotract(View SkinBlock)
    {

    }
}