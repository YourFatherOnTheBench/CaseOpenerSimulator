package com.example.caseopener;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

        GridLayout main_container = findViewById(R.id.block_container);
        String[] image_names = {"case1.png", "case2.png"};

        List<Case> cases = new ArrayList<>();
        cases.add(new Case("Ben Case", "case1.png"));
        cases.add(new Case("Jurson Case", "case1.png"));
        cases.add(new Case("Igor Case", "case1.png"));
        cases.add(new Case("Blazej Case", "case1.png"));
        cases.add(new Case("Michal Case", "case1.png"));
        cases.add(new Case("Ben Case", "case1.png"));
        cases.add(new Case("Ben Case", "case1.png"));
        cases.add(new Case("Ben Case", "case1.png"));



        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < cases.toArray().length; i++) {
            View caseBlock = inflater.inflate(R.layout.caseblock, main_container, false);
            // caseBlock.setBackgroundColor(ContextCompat.getColor(this, colors[i])); zmienianie koloru
            TextView case_name = caseBlock.findViewById(R.id.CaseName);
            ImageView case_image = caseBlock.findViewById(R.id.Case_img);

            case_name.setText(cases.get(i).getName());

            try(InputStream inputStream = getAssets().open(cases.get(i).getImgName())) {
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                case_image.setImageDrawable(drawable);
            } catch (IOException e) {

            }

            main_container.addView(caseBlock);
        }
    }
}
