package com.example.caseopener;


import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class CaseManager {


    public String[] CaseNames = {"Ben Case", "Ben Case", "Ben Case", "Ben Case",
            "Ben Case", "Ben Case", "Ben Case", "Ben Case",
            "Ben Case", "Ben Case", "Ben Case", "Ben Case"};
    public String[] CaseImages = {"case1.png", "case1.png", "case1.png", "case1.png",
            "case1.png", "case1.png", "case1.png", "case1.png",
            "case1.png", "case1.png", "case1.png", "case1.png",};
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
            Integer id = i+1;
            Case newCase = new Case(id.toString(), CaseNames[i], CaseImages[i]);
            ArrayList<String> skins = new ArrayList<>();

            for(int j = 0; j < 26; j++)
            {
                skins.add(SkinManager.getInstance().skinList.get(j*12).getId());
            }
            newCase.setSkins(skins);

            cases.add(newCase);
        }
    }





}
