package com.example.caseopener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CaseOpening extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_case_opening);


        GridLayout BlockSkin = findViewById(R.id.SkinBlock);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        Intent intent = getIntent();
        String Case_id = intent.getStringExtra("Case_id");
        int Case_position = intent.getIntExtra("position", 0);






        for(int i = 0; i < CaseManager.getInstance().cases.get(Case_position).skins.size(); i++)
        {
            Log.d("Case", "Case data: " + SkinManager.getInstance().skinList.id );
        }



    }
}