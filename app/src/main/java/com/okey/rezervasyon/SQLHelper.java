package com.okey.rezervasyon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    public SQLHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table userLoginInfo(username TEXT primary key,password TEXT," +
                "email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists userLoginInfo");

        onCreate(sqLiteDatabase);

    }

    public Boolean insertData(String username,String password,String email){
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("email",email);
        long result = MyDB.insert("userLoginInfo",null,contentValues);
        if (result==-1) return false;
        else
            return true;
    }


    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from userLoginInfo where username = ?",new String[]{username});

        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    public Boolean authentication(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from userLoginInfo where username = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
