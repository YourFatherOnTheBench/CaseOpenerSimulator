package com.example.caseopener;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class OpenedCaseLoot extends AppCompatActivity {


    private Skin CurrentSkin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_case_loot);










        //Seting dropped skin


        //Funcyionality buttos
        Button Sell;
        Button Eq;
        Button Open;
        String SkinID = getIntent().getStringExtra("SkinID");
        int Case_ID = getIntent().getIntExtra("CaseID",0);
        Button MainMenu;
        LinearLayout dropContainer = findViewById(R.id.DropedSkin);
        PlayAnimation( 3500, SkinID);
        CurrentSkin = SkinManager.getInstance().skins_database.get(SkinID);
        Sell = findViewById(R.id.Sell);
        Sell.setOnClickListener(v -> {
            EqManager.getInstance().removeSkin(this, SkinID);

            Double price = Double.parseDouble(CurrentSkin.getPrice());
            Balance.getInstance().SellSkin(this,price);

            Intent intentHere = new Intent(OpenedCaseLoot.this, CaseOpening.class);
            intentHere.putExtra("SkinID", SkinID);
            intentHere.putExtra("position", Case_ID);
            startActivity(intentHere);
        });

        Eq = findViewById(R.id.EQ);
        Eq.setOnClickListener(v -> {
            Intent intentHere = new Intent(OpenedCaseLoot.this, EQ_page.class);
            startActivity(intentHere);
        });

        Open = findViewById(R.id.OpenNext);
        Open.setOnClickListener(v -> {
            String NewSkinID = SkinManager.RandomSkin_Generator(Case_ID);
            ImageView gifView = findViewById(R.id.GifAnimation);
            gifView.setVisibility(View.VISIBLE);
            dropContainer.removeAllViews();

            PlayAnimation(3500, NewSkinID);
            //CurrentSkin = SkinManager.getInstance().skins_database.get(NewSkinID);

        });

        MainMenu = findViewById(R.id.MainMenu);
        MainMenu.setOnClickListener(v -> {
            Intent intentHere = new Intent(OpenedCaseLoot.this, MainActivity.class);
            startActivity(intentHere);
        });
    }
    public void SetDroppedSkin(String SkinID)
    {

        LinearLayout dropContainer = findViewById(R.id.DropedSkin);

        //removes previous skins
        dropContainer.removeAllViews();

        // Inflate block.xml
        LayoutInflater inflater = LayoutInflater.from(this);
        View skinBlock = inflater.inflate(R.layout.block, dropContainer, false);

        LinearLayout skinBackground = skinBlock.findViewById(R.id.skin);

        Skin currentSkin = SkinManager.getInstance().skins_database.get(SkinID);
        String id = currentSkin.getId();
        EqManager.getInstance().addSkin(this, id);

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

    public void PlayAnimation( long AnimationTime, String SkinId)
    {


        ImageView gifView = findViewById(R.id.GifAnimation);

        Glide.with(this).asGif().load(R.drawable.gif2).into(gifView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                gifView.setVisibility(View.GONE);
                //Glide.with(OpenedCaseLoot.this).clear(gifView);
                SetDroppedSkin(SkinId);

            }
        }, AnimationTime);

    }
}
