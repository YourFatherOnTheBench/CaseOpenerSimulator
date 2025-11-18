package com.example.caseopener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CaseOpening extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_case_opening);

        GridLayout BlockSkin = findViewById(R.id.SkinBlock);
        LayoutInflater Inflater = LayoutInflater.from(this);

        Intent intent = getIntent();
        String Case_id = intent.getStringExtra("Case_id");
        int Case_position = intent.getIntExtra("position", 0);

        ImageView CaseImage = findViewById(R.id.CaseImage);
        TextView CaseName = findViewById(R.id.CaseOpeningName);

        CaseName.setText(CaseManager.getInstance().cases.get(Case_position).getName());

        try (InputStream inputStream = getAssets().open(CaseManager.getInstance().CaseImages[Case_position])) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            CaseImage.setImageDrawable(drawable);
        } catch (IOException e) {

        }

        for (int i = 0; i < CaseManager.getInstance().cases.get(Case_position).skins.size(); i++) {
            View Skin_block = Inflater.inflate(R.layout.block, BlockSkin, false);
            String id = CaseManager.getInstance().cases.get(Case_position).skins.get(i);

            LinearLayout skinBackground = Skin_block.findViewById(R.id.skin);

            Skin currentSkin = SkinManager.getInstance().skins_database.get(id);

            if (currentSkin != null) {
                TextView name = Skin_block.findViewById(R.id.nameSkin);
                TextView rarity = Skin_block.findViewById(R.id.raritySkin);
                TextView wear = Skin_block.findViewById(R.id.wearSkin);
                ImageView skinImage = Skin_block.findViewById(R.id.imageSkin);

                name.setText(currentSkin.getName());
                wear.setText(currentSkin.getWear());
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

            BlockSkin.addView(Skin_block);
        }

        Button back_btn = findViewById(R.id.Back_to_menu_btn);

        back_btn.setOnClickListener(v -> {
            Intent intentHere = new Intent(CaseOpening.this, MainActivity.class);
            startActivity(intentHere);
        });
    }
}
