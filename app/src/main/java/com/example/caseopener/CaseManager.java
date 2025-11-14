package com.example.caseopener;


import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class CaseManager {

    public static CaseManager instance;

    public static CaseManager getInstance() {
        if (instance == null) {
            instance = new CaseManager();
        }
        return instance;

    }
    public ArrayList<Case> cases = new ArrayList<>();


    public void loadCases(Context context)
    {
        for(int i = 0; i < 12; i++)
        {
            Case newCase = new Case("Ben Case ", "case1.png");
            cases.add(newCase);
        }
    }





}
