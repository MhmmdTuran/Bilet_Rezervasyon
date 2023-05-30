package com.okey.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class UserInformations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_informations);

        try {
            addItemsFromJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addItemsFromJSON() throws IOException {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < jsonArray.length(); ++i){
                JSONObject itemObj = jsonArray.getJSONObject(i);

                String name = itemObj.getString("name");

                Log.e("bak",  "my boi" + name);

            }
            //Log.e("bak","BASARILI" + jsonArray);

        } catch (JSONException e){
            Log.d("bak","BASARISIZ additemsfromjson",e);
        }
    }

    private String readJSONDataFromFile() {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.otogarlar);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream,"UTF-8"));

            while ((jsonString=bufferedReader.readLine()) != null){
                builder.append(jsonString);
            }

        } catch (UnsupportedEncodingException e) {
            Log.d("bak","asa0");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return new String(builder);
    }
}