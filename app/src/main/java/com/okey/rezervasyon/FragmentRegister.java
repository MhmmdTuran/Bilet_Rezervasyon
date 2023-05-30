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

public class FragmentRegister extends Fragment {


    EditText username,password,email;
    Button register;

    SQLHelper DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("bak","register create");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        DB = new SQLHelper(getContext());

        username = root.findViewById(R.id.txtUsernameRegister);
        password = root.findViewById(R.id.txtPasswordRegister);
        email = root.findViewById(R.id.txtEmailRegister);
        register = root.findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString();

                if(user.equals("") || pass.equals("") || mail.equals(""))
                    Toast.makeText(getContext(),"Lütfen boş yerleri doldurunuz",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUsername(user);
                    if(checkUser==false) {
                        Boolean insert = DB.insertUserData(user,pass,mail);
                        if(insert){
                            Toast.makeText(getContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), Home.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getContext(),"Kayıt Başarısız",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(getContext(),"Bu Kullanıcı Adı Zaten Mevcut",Toast.LENGTH_SHORT).show();
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
        Log.e("bak","register start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("bak","register resume");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("bak","register attach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("bak","register pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("bak","register destroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("bak","register destroyview");
    }
}