package com.example.caseopener;


import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class CaseManager {


    public String[] CaseNames = {"Dracula Case", "Rich Case", "Gamer Case", "... Case",
            "Ben Case", "Poland Case", "Drunk Case", "UFO Case",
            "Streamer Case", "Winter Case", "Cobra Case", "Nerdzik Case"};
    public String[] CaseImages = {"case1.png", "case2.png", "case3.png", "case4.png",
            "case12.png", "case6.png", "case7.png", "case8.png",
            "case9.png", "case10.png", "case11.png", "case5.png",};
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
        if (cases.isEmpty()) {
            for(int i = 0; i < 12; i++)
            {
                Integer id = i+1;
                Case newCase = new Case(id.toString(), CaseNames[i], CaseImages[i]);
                ArrayList<String> skins = new ArrayList<>();

                for(int j = 0; j < 26; j++)
                {
                    skins.add(SkinManager.getInstance().skinList.get((i * 26) + j).getId());
                }
                newCase.setSkins(skins);

                cases.add(newCase);
            }
        }
    }




}