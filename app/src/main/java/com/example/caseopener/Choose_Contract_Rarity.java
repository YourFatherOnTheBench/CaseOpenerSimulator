package com.example.caseopener;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    Toast cur_toast;
    Map<String, Integer> rarities = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_contract_rarity);


        Button Deposit = findViewById(R.id.CurrentDeposit);
        Deposit.setText(Balance.getInstance().GetDepositString());
        Button menuButton = findViewById(R.id.Back_to_menu_btn);



        rarities.put("Consumer Grade", 0);
        rarities.put("Mil-Spec Grade", 0);
        rarities.put("Restricted", 0);
        rarities.put("Classified", 0);
        rarities.put("Covert", 0);
        rarities.put("Extraordinary", 0);

        for(int i = 0; i < EqManager.getInstance().acquiredSkins.size(); i++) {

            Log.d("EQ",EqManager.getInstance().acquiredSkins.get(i));

            String id = EqManager.getInstance().acquiredSkins.get(i);
            Skin currentSkin = SkinManager.getInstance().skins_database.get(id);

            if (currentSkin != null) {
                Log.d("CURRENT SKIN: Rarity", currentSkin.getRarity());
                try {
                    JSONObject rarityJson = new JSONObject(currentSkin.getRarity());
                    String rarityName = rarityJson.getString("name");
                    if (rarities.containsKey(rarityName)) {
                        int currentCount = rarities.get(rarityName);
                        if (currentCount < 10) {
                            rarities.put(rarityName, currentCount + 1);
                        }
                        Log.d("ilosckinow", rarities.get(rarityName).toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        TextView Blue = findViewById(R.id.Blue);
        Blue.setText(rarities.get("Consumer Grade").toString() + "/10");
        Blue.setOnClickListener(v -> {
            if (rarities.get("Consumer Grade") < 10) {
                if (cur_toast != null) {
                    cur_toast.cancel();
                }
                cur_toast = Toast.makeText(this, "Za mało skinów do kontrkatu" + rarities.get("Consumer Grade") + " / 10)", Toast.LENGTH_SHORT);
                cur_toast.show();
                return;
            }
            Intent intent = new Intent(Choose_Contract_Rarity.this, Contract.class);
            intent.putExtra("RarityID", 0);
            startActivity(intent);
        });

        TextView Purple = findViewById(R.id.Purple);
        Purple.setText(rarities.get("Mil-Spec Grade").toString() + "/10");
        Purple.setOnClickListener(v -> {
            if (rarities.get("Mil-Spec Grade") < 10) {
                if (cur_toast != null) {
                    cur_toast.cancel();
                }
                cur_toast = Toast.makeText(this, "Za mało skinów do kontrkatu \n(" + rarities.get("Mil-Spec Grade") + " / 10)", Toast.LENGTH_SHORT);
                cur_toast.show();
                return;
            }
            Intent intent = new Intent(Choose_Contract_Rarity.this, Contract.class);
            intent.putExtra("Rarity", 1);
            startActivity(intent);
        });
        TextView Pink = findViewById(R.id.Pink);
        Pink.setText(rarities.get("Restricted").toString() + "/10");
        Pink.setOnClickListener(v -> {
            if (rarities.get("Restricted") < 10) {
                if (cur_toast != null) {
                    cur_toast.cancel();
                }
                cur_toast = Toast.makeText(this, "Za mało skinów do kontrkatu \n(" + rarities.get("Restricted") + " / 10)", Toast.LENGTH_SHORT);
                cur_toast.show();
                return;
            }
            Intent intent = new Intent(Choose_Contract_Rarity.this, Contract.class);
            intent.putExtra("Rarity", 2);
            startActivity(intent);
        });

        TextView Red = findViewById(R.id.Red);
        Red.setText(rarities.get("Classified").toString() + "/10");
        Red.setOnClickListener(v -> {
            if (rarities.get("Classified") < 10) {
                if (cur_toast != null) {
                    cur_toast.cancel();
                }
                cur_toast = Toast.makeText(this, "Za mało skinów do kontrkatu \n(" + rarities.get("Classified") + " / 10)", Toast.LENGTH_SHORT);
                cur_toast.show();
                return;
            }
            Intent intent = new Intent(Choose_Contract_Rarity.this, Contract.class);
            intent.putExtra("Rarity", 3);
            startActivity(intent);
        });

        TextView Gold = findViewById(R.id.Gold);
        Gold.setText(rarities.get("Covert").toString() + "/10");

        Gold.setOnClickListener(v -> {
            if (rarities.get("Covert") < 10) {
                if (cur_toast != null) {
                    cur_toast.cancel();
                }
                cur_toast = Toast.makeText(this, "Za mało skinów do kontrkatu \n(" + rarities.get("Covert") + " / 10)", Toast.LENGTH_SHORT);
                cur_toast.show();
                return;
            }
            Intent intent = new Intent(Choose_Contract_Rarity.this, Contract.class);
            intent.putExtra("Rarity", 4);
            startActivity(intent);
        });
        ImageButton eq_btn = findViewById(R.id.eq_btn);
        eq_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, EQ_page.class);
            startActivity(intent);

        });

        menuButton.setOnClickListener(v -> {
            finish();
        });
    }

}
