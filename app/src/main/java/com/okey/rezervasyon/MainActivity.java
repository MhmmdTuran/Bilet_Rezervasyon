package com.okey.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        FragmentManager fragmentManager = getSupportFragmentManager();





        Button btnLoginChs = findViewById(R.id.btnLoginChoose);
        btnLoginChs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerLoginForm, FragmentLogin.class,null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });




        Button btnRegisterChs = findViewById(R.id.btnRegisterChoose);
        btnRegisterChs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerLoginForm, FragmentRegister.class,null)
                        .setReorderingAllowed(true)
                        .commit();

            }
        }
        );






    }
}