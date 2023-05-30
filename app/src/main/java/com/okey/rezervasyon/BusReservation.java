package com.okey.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;


public class BusReservation extends AppCompatActivity {

    String username,fromWhere,toWhere,resDate;

    SQLHelper DB;
    Button btnReserv;
    AutoCompleteTextView txtFromWhere,txtToWhere;

    EditText nameSurname;
    String[] Cities = new String[81];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_reservation);

        DB = new SQLHelper(getApplicationContext());

        txtFromWhere = findViewById(R.id.autotxtCityFromWhere);
        btnReserv = findViewById(R.id.btnReservation);
        txtToWhere = findViewById(R.id.autotxtCityToWhere);
        nameSurname = findViewById(R.id.txtPersonName);


        try {
            addItemsFromJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, Cities);
        txtFromWhere.setAdapter(adapter);
        txtToWhere.setAdapter(adapter);


        btnReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                username = intent.getStringExtra("username");
                Date datenow = new Date();

                resDate = datenow.toString();
                fromWhere = txtFromWhere.getText().toString();
                toWhere = txtToWhere.getText().toString();
              //  Toast.makeText(getApplicationContext(),username+" "+fromWhere+" "+toWhere+ " "+resDate,Toast.LENGTH_SHORT).show();

                try {
                    if(fromWhere.equals("") || toWhere.equals("")){
                        Toast.makeText(getApplicationContext(),"Lütfen Şehir Bilgilerni Doldurun",Toast.LENGTH_SHORT).show();
                    } else if (fromWhere == toWhere) {
                        Toast.makeText(getApplicationContext(),"Lütfen Farklı Şehir Bilgileri Seçin",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean insert = DB.insertResData(username,fromWhere,toWhere,resDate);
                        if(insert)
                        {
                            Toast.makeText(getApplicationContext(),"Bilet Rezervasyonu Başarıyla Tamamlandı",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Bilet Rezervasyonu Başarısız",Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Bilet Rezervasyonu Başarısız",Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }

                String notiMsg = "Sn." + nameSurname +", "+ fromWhere+"-"+toWhere+" Seferiniz için iyi yolculuklar dileriz.";

                Notification.InboxStyle style = new Notification.InboxStyle();
                style.setBigContentTitle("Rezervasyon");


                NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(BusReservation.this);

                builder.setContentTitle("Otobüs Rezervasyonu");
                builder.setContentText(notiMsg);
                builder.setSmallIcon(R.drawable.bilet);
                builder.setAutoCancel(true);
                builder.setTicker("Teşekkürler");
                builder.setStyle(style);

                Intent intent1 = new Intent();
                PendingIntent pendingIntent = PendingIntent.getActivity(BusReservation.this,1,intent,0);
                builder.setContentIntent(pendingIntent);

                Notification notification = new Notification();
                manager.notify(1,notification);
            }
        });
    }



    private void addItemsFromJSON() throws IOException, JSONException {
        String jsonDataString = readJSONDataFromFile();

        Gson gson = new Gson();

        Type type = new TypeToken<List<Country>>(){}.getType();
        List<Country> list = gson.fromJson(jsonDataString, type);

        for (int i = 0; i < list.size() ; i++) {
            Cities[i] = String.valueOf(list.get(i).il_adi);
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

