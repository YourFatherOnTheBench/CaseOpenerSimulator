package com.example.caseopener;



import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Choose_Contract_Rarity extends AppCompatActivity {

    Map<String, Integer> rarities = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_contract_rarity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rarities.put("Consumer Grade", 0);
        rarities.put("Mil-Spec Grade", 0);
        rarities.put("Restricted", 0);
        rarities.put("Classified", 0);
        rarities.put("Covert", 0);

        for(int i = 0; i < EqManager.getInstance().acquiredSkins.size(); i++) {

            Log.d("EQ",EqManager.getInstance().acquiredSkins.get(i));

            String id = EqManager.getInstance().acquiredSkins.get(i);
            Skin currentSkin = SkinManager.getInstance().skins_database.get(id);

            Log.d("CURRENT SKIN: Rarity", currentSkin.getRarity());

            if (currentSkin != null) {
                try {
                    JSONObject rarityJson = new JSONObject(currentSkin.getRarity());
                    String rarityName = rarityJson.getString("name");
                    rarities.put(rarityName, rarities.getOrDefault(rarityName, 0) + 1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView Blue = findViewById(R.id.Blue);
        Blue.setText(rarities.get("Consumer Grade").toString() + "/10");

        TextView Purple = findViewById(R.id.Purple);
        Purple.setText(rarities.get("Mil-Spec Grade").toString() + "/10");

        TextView Pink = findViewById(R.id.Pink);
        Pink.setText(rarities.get("Restricted").toString() + "/10");

        TextView Red = findViewById(R.id.Red);
        Red.setText(rarities.get("Classified").toString() + "/10");

        TextView Gold = findViewById(R.id.Gold);
        Gold.setText(rarities.get("Covert").toString() + "/10");
    }
}