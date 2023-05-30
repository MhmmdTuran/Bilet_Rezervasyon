package com.okey.rezervasyon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentLogin extends Fragment {



    EditText username,password;
    Button btnLogin;
    SQLHelper DB;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("bak","login oncreateview");

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login,container,false);

        DB = new SQLHelper(getContext());

        username = root.findViewById(R.id.txtUsernameLogin);
        password = root.findViewById(R.id.txtPasswordLogin);
        btnLogin = root.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(getContext(),"Lütfen Bir Kullanıcı Adı Ve Şifre Giriniz",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpas = DB.authentication(user,pass);
                    if(checkuserpas){
                        Toast.makeText( getContext(),"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), Home.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getContext(),"Kullanıcı Adı Veya Şifre Yanlış",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("bak","login start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("bak","login resume");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("bak","login attach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("bak","login pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("bak","login destroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("bak","login destroyview");
    }
}