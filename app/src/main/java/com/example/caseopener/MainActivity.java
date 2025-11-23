package com.example.caseopener;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] colors = {R.color.gray, R.color.darkgray, R.color.black, R.color.white, R.color.navy, R.color.darkblue, R.color.darknavy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout main_container = findViewById(R.id.block_container); // glowny block gdzie sa skrzynki
        LayoutInflater inflater = LayoutInflater.from(this); // do tego dodajesz block z skrzynka

        SkinManager.getInstance().ReadFromJSON(this, "skins_final.json");
        CaseManager.getInstance().loadCases(this); // utworzenie caseManager
        EqManager.getInstance().loadSkins(this);

        Button Deposit = findViewById(R.id.CurrentDeposit);
        Deposit.setText(EqManager.getInstance().GetMoenyString(this));



        ImageButton eq_btn = findViewById(R.id.eq_btn);
        eq_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EQ_page.class);
            startActivity(intent);

        });








        // Only add views if the container is empty
        if (main_container.getChildCount() == 0) {
            // dodawanie skrzynek do bloku i mozliwosc zmieniania cech tych blockow
            for (int i = 0; i < CaseManager.getInstance().cases.size(); i++) {
                View caseBlock = inflater.inflate(R.layout.caseblock, main_container, false);
                caseBlock.setBackgroundColor(Color.parseColor("#0D2338"));
                TextView case_name = caseBlock.findViewById(R.id.CaseName);
                ImageView case_image = caseBlock.findViewById(R.id.Case_img);
                Button btn = caseBlock.findViewById(R.id.case_btn);
                btn.setBackgroundColor(Color.parseColor("#375876"));

                int finalI = i;
                Log.d("Position", "Position: " + finalI);
                btn.setOnClickListener(v ->
                        {
                            Intent intent = new Intent(MainActivity.this, CaseOpening.class);
                            intent.putExtra("Case_id", CaseManager.getInstance().cases.get(finalI).getId());
                            intent.putExtra("position", finalI);
                            startActivity(intent);
                        }
                );

                case_name.setText(CaseManager.getInstance().cases.get(i).getName());

                try (InputStream inputStream = getAssets().open(CaseManager.getInstance().cases.get(i).getImgName())) {
                    Drawable drawable = Drawable.createFromStream(inputStream, null);
                    case_image.setImageDrawable(drawable);
                } catch (IOException e) {

                }

                main_container.addView(caseBlock);
            }
        }
    }
}
