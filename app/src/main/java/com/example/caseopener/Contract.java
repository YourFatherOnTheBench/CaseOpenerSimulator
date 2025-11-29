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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contract extends AppCompatActivity {
    //MAPA SKINOW W KONTRAKCIE
    Map<Integer, String> Skins_in_Contract = new HashMap<>();
    Toast cur_toast;

    ArrayList<String> Eq_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contract);


        GridLayout gridLayout = findViewById(R.id.SkinBlockEQ);
        LayoutInflater inflater = LayoutInflater.from(this);


        Button Deposit = findViewById(R.id.CurrentDeposit);
        Deposit.setText(Balance.getInstance().GetDepositString());

        Log.d("EQ", EqManager.getInstance().acquiredSkins.toString());



        Button menuButton = findViewById(R.id.Back_to_menu_btn);

        String contractRarity = getIntent().getStringExtra("Rarity");

        for(int i = 0; i < EqManager.getInstance().acquiredSkins.size(); i++) {

            String id = EqManager.getInstance().acquiredSkins.get(i);
            Skin currentSkin = SkinManager.getInstance().skins_database.get(id);

            if (currentSkin != null) {
                try {
                    JSONObject rarityJson = new JSONObject(currentSkin.getRarity());
                    String rarityName = rarityJson.getString("name");

                    if (rarityName.equals(contractRarity)) {
                        View Skin_block = inflater.inflate(R.layout.block, gridLayout, false);

                        LinearLayout skinBackground = Skin_block.findViewById(R.id.skin);
                        TextView name = Skin_block.findViewById(R.id.nameSkin);
                        TextView rarity = Skin_block.findViewById(R.id.raritySkin);
                        TextView price = Skin_block.findViewById(R.id.Price);
                        ImageView skinImage = Skin_block.findViewById(R.id.imageSkin);

                        name.setText(currentSkin.getName());
                        price.setText(currentSkin.getPrice() + "$");
                        Glide.with(this).load(currentSkin.getImage()).into(skinImage);

                        String colorName = rarityJson.getString("color").toUpperCase();
                        rarity.setText(rarityName);
                        skinBackground.setBackgroundColor(Color.parseColor(colorName));

                        //Klikiecie na skiny dodaje je do kontraktu
                        Skin_block.setId(i);

                        Skin_block.setOnClickListener(v -> {
                            int SkinPlaceInEq = Skin_block.getId();
                            if (!(Skins_in_Contract.containsKey(SkinPlaceInEq)) && Skins_in_Contract.size() < 10) {

                                Skins_in_Contract.put(SkinPlaceInEq, EqManager.getInstance().acquiredSkins.get(SkinPlaceInEq));
                                Log.d("Info o skinie", "dodano skina, Ilosc skinow : " + Skins_in_Contract.size());
                                if (cur_toast != null) {
                                    cur_toast.cancel();
                                }
                                cur_toast = Toast.makeText(this, "Skin dodany do kontraktu(" + Skins_in_Contract.size() + " / 10)", Toast.LENGTH_SHORT);
                                cur_toast.show();
                                //animacja zaznaczenia
                                Skin_block.animate()
                                        .scaleX(0.95f)
                                        .scaleY(0.95f)
                                        .setDuration(200)
                                        .start();
                            } else if (Skins_in_Contract.containsKey(SkinPlaceInEq)) {
                                Skins_in_Contract.remove(SkinPlaceInEq);
                                Log.d("Info o skinie", "Juz byl ten skin");
                                if (cur_toast != null) {
                                    cur_toast.cancel();
                                }
                                cur_toast = Toast.makeText(this, "Skin usuniety z kontraktu(" + Skins_in_Contract.size() + " / 10)", Toast.LENGTH_SHORT);
                                cur_toast.show();
                                //animacja odznaczenia
                                Skin_block.animate()
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(200)
                                        .start();

                            } else if (Skins_in_Contract.size() == 10) {
                                if (cur_toast != null) {
                                    cur_toast.cancel();
                                }
                                cur_toast = Toast.makeText(this, "Kontrakt gotowy (" + Skins_in_Contract.size() + " / 10)", Toast.LENGTH_SHORT);
                                cur_toast.show();
                            }
                        });
                        gridLayout.addView(Skin_block);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


        menuButton.setOnClickListener(v -> {
            finish();
        });
    }
    public void Chose_Skin_Rarity(String rarity)
    {

    }
}