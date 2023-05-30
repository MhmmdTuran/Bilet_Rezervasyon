package com.okey.rezervasyon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.util.Date;

public class SQLHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    public SQLHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table userLoginInfo(username TEXT primary key,password TEXT," +
                "email TEXT)");
        sqLiteDatabase.execSQL("create Table busReservations(username TEXT primary key,fromWhere TEXT," +
                "toWhere TEXT,resDate date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists userLoginInfo");
        sqLiteDatabase.execSQL("drop Table if exists busReservations");
        onCreate(sqLiteDatabase);
    }

    public Boolean insertResData(String username, String fromWhere, String toWhere, String resDate){
        SQLiteDatabase myDB =getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("fromWhere",fromWhere);
        contentValues.put("toWhere",toWhere);
        contentValues.put("resDate",resDate);
        long result = myDB.insert("busReservations",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertUserData(String username, String password, String email){
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
