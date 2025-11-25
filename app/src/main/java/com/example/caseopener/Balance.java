package com.example.caseopener;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class Balance {

    public double deposit = 0.0;
    // You changed this from "balance.json" to "deposit.json".
    // This effectively resets the user's money because the new file doesn't exist yet.
    private static final String FILENAME = "deposit.json";

    private static Balance instance;

    public static Balance getInstance() {
        if (instance == null) {
            instance = new Balance();
        }
        return instance;
    }

    public void saveDeposit(Context context) {
        try {
            JSONObject DepositObj = new JSONObject();
            DepositObj.put("balance", deposit);

            String jsonString = DepositObj.toString();
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();

            Log.d("BalanceManager", "Saved balance: " + deposit);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDeposit(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            // Move Logs here to verify content
            Log.d("BalanceManager", "File Content: " + sb.toString());

            if (sb.length() == 0) return;

            JSONObject balanceJson = new JSONObject(sb.toString());
            deposit = balanceJson.optDouble("balance", 0.0);

            fis.close();
            Log.d("BalanceManager", "Loaded balance: " + deposit);

        } catch (FileNotFoundException e) {
            // THIS is likely where your code was going before!
            Log.e("BalanceManager", "File not found (First run?). Starting with $0.00");
            deposit = 0.0;
        } catch (IOException | JSONException e) {
            Log.e("BalanceManager", "Error loading balance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public double GetDeposit(){ return deposit; }
    public String GetDepositString(){ return String.format("$%.2f", deposit); }

    public void SellSkin(Context context, double price) {
        deposit += price;
        saveDeposit(context);
    }
}