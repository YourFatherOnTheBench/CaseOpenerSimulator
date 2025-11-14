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

        SkinManager.getInstance().ReadFromJSON(this, "skins_reduced.json"); // utworzenie skinManager
        // Jesli chcesz wejsc do listy skinow to musisz napisac SkinManager.getInstance().skinList;


        CaseManager.getInstance().loadCases(this); // utworzenie caseManager
        // Jesli chcesz wejsc do listy skrzyn to musisz napisac CaseManager.getInstance().cases;



        // dodawanie skrzynek do bloku i mozliwosc zmieniania cech tych blockow
        for (int i = 0; i < CaseManager.getInstance().cases.size(); i++) {
            View caseBlock = inflater.inflate(R.layout.caseblock, main_container, false);
            // caseBlock.setBackgroundColor(ContextCompat.getColor(this, colors[i])); zmienianie koloru
            TextView case_name = caseBlock.findViewById(R.id.CaseName);
            ImageView case_image = caseBlock.findViewById(R.id.Case_img);
            Button btn = caseBlock.findViewById(R.id.case_btn);

            int finalI = i;
            btn.setOnClickListener(v ->
                    {
                        Intent intent = new Intent(MainActivity.this, EQ_page.class);
                        intent.putExtra("Case_id", CaseManager.getInstance().cases.get(finalI).getId());
                        startActivity(intent);
                    }
            );




            case_name.setText(CaseManager.getInstance().cases.get(i).getName());





            try(InputStream inputStream = getAssets().open(CaseManager.getInstance().cases.get(i).getImgName())) {
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                case_image.setImageDrawable(drawable);
            } catch (IOException e) {

            }

            main_container.addView(caseBlock);
        }
    }
}
