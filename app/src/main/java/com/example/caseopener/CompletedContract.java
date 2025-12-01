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

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class CompletedContract extends AppCompatActivity {

    private Skin currentSkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_contract);

        String skinID = getIntent().getStringExtra("ContractRarityID");
        this.currentSkin = SkinManager.getInstance().skins_database.get(skinID);

        if (this.currentSkin != null) {
            SetDroppedSkin();
        } else {
            // Handle error: Skin not found
            finish(); // or show a toast
            return;
        }

        Button sellButton = findViewById(R.id.Sell);
        sellButton.setOnClickListener(v -> {
            EqManager.getInstance().removeSkin(this, currentSkin.getId());
            double price = Double.parseDouble(currentSkin.getPrice());
            Balance.getInstance().SellSkin(this, price);
            Intent intent = new Intent(CompletedContract.this, MainActivity.class);
            startActivity(intent);
        });

        Button eqButton = findViewById(R.id.EQ);
        eqButton.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedContract.this, EQ_page.class);
            startActivity(intent);
        });

        Button mainMenuButton = findViewById(R.id.MainMenu);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedContract.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void SetDroppedSkin() {
        LinearLayout dropContainer = findViewById(R.id.DropedSkin);
        dropContainer.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(this);
        View skinBlock = inflater.inflate(R.layout.block, dropContainer, false);

        LinearLayout skinBackground = skinBlock.findViewById(R.id.skin);

        // Add the new skin to the inventory
        EqManager.getInstance().addSkin(this, this.currentSkin.getId());

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

        // Add the view and animate it for the grand reveal!
        skinBlock.setAlpha(0f);
        dropContainer.addView(skinBlock);
        skinBlock.animate().alpha(1f).setDuration(1500).start();
    }
}
