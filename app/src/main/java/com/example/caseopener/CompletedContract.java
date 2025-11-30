package com.example.caseopener;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class CompletedContract extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completed_contract);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String skinID = getIntent().getStringExtra("ContractRarityID");
        SetDroppedSkin(skinID);
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
}