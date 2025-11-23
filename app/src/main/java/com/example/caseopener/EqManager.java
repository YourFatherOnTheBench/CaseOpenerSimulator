package com.example.caseopener;

import java.util.ArrayList;

public class EqManager {


    private static EqManager instance;

    public ArrayList<String> AcuairedSkins = new ArrayList<String>();


    public static EqManager getInstance() {
        if (instance == null) {
            instance = new EqManager();
        }
        return instance;
    }

    private EqManager() {
    }



}
