package com.okey.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    String username;
    Button cikis;
    Button bus;
    Button hotel;
    Button userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        cikis = findViewById(R.id.btnCikis);

        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        bus = findViewById(R.id.btnBus);

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BusReservation.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });

        hotel = findViewById(R.id.btnHotel);

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HotelReservation.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


        userInfo = findViewById(R.id.btnUserInfo);

        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserInformations.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });










    }
}