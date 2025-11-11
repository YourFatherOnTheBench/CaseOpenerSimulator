package com.example.caseopener;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Skin> list = new ArrayList<>();
        adapter = new GridAdapter(list);

        RecyclerView recyclerView = findViewById(R.id.block_container);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        adapter.addItem(new Skin("skin name", "rare", "field-tested"));
        adapter.addItem(new Skin("skin name", "rare", "field-tested"));
        adapter.addItem(new Skin("skin name", "rare", "field-tested"));
    }
}
