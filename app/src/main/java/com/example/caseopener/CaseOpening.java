package com.example.caseopener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
        LayoutInflater Inflater = LayoutInflater.from(this);

        Intent intent = getIntent();
        String Case_id = intent.getStringExtra("Case_id");
        int Case_position = intent.getIntExtra("position", 0);






        for(int i = 0; i < CaseManager.getInstance().cases.get(Case_position).skins.size(); i++)
        {
            View Skin_block = Inflater.inflate(R.layout.block, BlockSkin, false);

            String id = CaseManager.getInstance().cases.get(Case_position).skins.get(i);

            Log.d("Case", "Case data: " + SkinManager.getInstance().skins_database );
        }

        Button back_btn = findViewById(R.id.Back_to_menu_btn);

        back_btn.setOnClickListener(v -> {
            Intent intentHere = new Intent(CaseOpening.this, MainActivity.class);
            startActivity(intentHere);
        });








    }
}