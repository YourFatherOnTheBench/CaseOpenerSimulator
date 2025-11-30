package com.example.caseopener;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.Toast;
import android.app.AlertDialog;
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



    Toast current_toast;
    ArrayList<Integer> ChosenSkins = new ArrayList<>();
    ArrayList<String> ChosenIds = new ArrayList<>();
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq_page);
        ChosenIds.clear();
        ChosenSkins.clear();





        GridLayout gridLayout = findViewById(R.id.SkinBlockEQ);
        LayoutInflater inflater = LayoutInflater.from(this);
        EqManager.getInstance().loadSkins(this);
        Balance.getInstance().loadDeposit(this);

        Button Deposit = findViewById(R.id.CurrentDeposit);
        Deposit.setText(Balance.getInstance().GetDepositString());
        Button menuButton = findViewById(R.id.Back_to_menu_btn);

        Log.d("EQ", EqManager.getInstance().acquiredSkins.toString());







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
            Skin_block.setId(i);

            Skin_block.setOnClickListener(v ->{
                int BlockId = v.getId();
                if(!ChosenSkins.contains(v.getId())) {

                    ChosenSkins.add(v.getId());
                    ChosenIds.add(EqManager.getInstance().acquiredSkins.get(BlockId));
                    Log.d("EQSKIN", ChosenIds.toString());

                    Skin_block.animate()
                            .scaleX(0.95f)
                            .scaleY(0.95f)
                            .setDuration(20)
                            .start();


                }
                else
                {
                    ChosenSkins.remove(v.getId());
                    ChosenIds.remove(EqManager.getInstance().acquiredSkins.get(BlockId));
                    Skin_block.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(20)
                            .start();
                }

            });





            gridLayout.addView(Skin_block);


        }
        Button sellButton = findViewById(R.id.SellSkins_btn);
        sellButton.setOnClickListener(v -> {
            if(ChosenIds.size() <= 0) {

                new AlertDialog.Builder(this)
                        .setTitle("Potwierdzenie sprzedaży")
                        .setMessage("Czy na pewno chcesz sprzedać wszystkie skiny?")
                        .setPositiveButton("Tak", (dialog, which) -> {
                                    for (int i = 0; i < EqManager.getInstance().acquiredSkins.size(); i++) {
                                       String id = EqManager.getInstance().acquiredSkins.get(i);

                                       Skin curr_skin = SkinManager.getInstance().skins_database.get(id);
                                       Balance.getInstance().SellSkin(this, Double.parseDouble(curr_skin.getPrice()));

                                    }
                                    EqManager.getInstance().acquiredSkins.clear();
                                    EqManager.getInstance().saveSkins(this);
                                    dialog.dismiss();
                                finish();
                                }).setNegativeButton("Nie", (dialog, which) -> {

                            dialog.cancel();

                        }).show();



//                current_toast = Toast.makeText(this, "Nie wybrano żadnych skinnów", Toast.LENGTH_LONG);
//                current_toast.show();
            }
            else
            {
                for(int i = 0; i < ChosenIds.size(); i++) {
                    String id = ChosenIds.get(i);
                    EqManager.getInstance().removeSkin(this, id);
                    Skin curr_skin = SkinManager.getInstance().skins_database.get(id);
                    Balance.getInstance().SellSkin(this, Double.parseDouble(curr_skin.getPrice()));
                }
                current_toast = Toast.makeText(this, "Pomyślnie sprzedano skiny", Toast.LENGTH_LONG);
                current_toast.show();
                finish();

            }




        });





        menuButton.setOnClickListener(v -> {
            finish();
        });







    }
    @Override
    protected void onResume() {
        super.onResume();

        EqManager.getInstance().loadSkins(this);
        Balance.getInstance().loadDeposit(this);

    }







}