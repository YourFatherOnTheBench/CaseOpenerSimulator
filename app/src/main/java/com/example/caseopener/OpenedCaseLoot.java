package com.example.caseopener;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenedCaseLoot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opened_case_loot);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Seting dropped skin
        String SkinID = getIntent().getStringExtra("SkinID");
        SetDroppedSkin(SkinID);
        //Funcyionality buttos
        Button Sell;
        Button Eq;
        Button Open;
        int Case_ID = getIntent().getIntExtra("CaseID",0);
        Button MainMenu;

        Sell = findViewById(R.id.Sell);
        Sell.setOnClickListener(v -> {
            Intent intentHere = new Intent(OpenedCaseLoot.this, CaseOpening.class);
            startActivity(intentHere);
        });

        Eq = findViewById(R.id.EQ);
        Eq.setOnClickListener(v -> {
            Intent intentHere = new Intent(OpenedCaseLoot.this, CaseOpening.class);
            startActivity(intentHere);
        });

        Open = findViewById(R.id.OpenNext);
        Open.setOnClickListener(v -> {
            String NewSkinID = CaseOpening.RandomSkin_Generator(Case_ID);
            SetDroppedSkin(NewSkinID);
        });

        MainMenu = findViewById(R.id.MainMenu);
        MainMenu.setOnClickListener(v -> {
            Intent intentHere = new Intent(OpenedCaseLoot.this, MainActivity.class);
            startActivity(intentHere);
        });
    }
    private void SetDroppedSkin(String SkinID)
    {

        LinearLayout dropContainer = findViewById(R.id.DropedSkin);
        //removes previous skins
        dropContainer.removeAllViews();

        // Inflate block.xml
        LayoutInflater inflater = LayoutInflater.from(this);
        View skinBlock = inflater.inflate(R.layout.block, dropContainer, false);

        LinearLayout skinBackground = skinBlock.findViewById(R.id.skin);

        Skin currentSkin = SkinManager.getInstance().skins_database.get(SkinID);

        if (currentSkin != null) {
            TextView name = skinBlock.findViewById(R.id.nameSkin);
            TextView rarity = skinBlock.findViewById(R.id.raritySkin);
            TextView price = skinBlock.findViewById(R.id.Price);
            ImageView skinImage = skinBlock.findViewById(R.id.imageSkin);

            name.setText(currentSkin.getName());
            price.setText(currentSkin.getPrice() + "$");
            Glide.with(this).load(currentSkin.getImage()).into(skinImage);

            try {
                JSONObject rarityJson = new JSONObject(currentSkin.getRarity());
                String rarityName = rarityJson.getString("name");
                String colorName = rarityJson.getString("color").toUpperCase();

                rarity.setText(rarityName);
                skinBackground.setBackgroundColor(Color.parseColor(colorName));

            } catch (JSONException e) {
                rarity.setText(currentSkin.getRarity());
                e.printStackTrace();
            }
        }

        // Add visual block to layout
        dropContainer.addView(skinBlock);


    }
}
