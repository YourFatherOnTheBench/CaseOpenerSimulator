package com.example.caseopener;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById.(R.id.block_container);

        try {
            JSONObject obj = jsonUtils.getJSON(this, "skins_reduced_5skins.json");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
