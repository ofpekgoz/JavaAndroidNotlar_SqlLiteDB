package com.omerfpekgoz.notlaruygulamasisqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotlarDao {

    public ArrayList<Notlar> tumNotlar(DbHelper dbHelper){

        ArrayList<Notlar> notlarArrayList=new ArrayList<>();
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        Cursor c=db.rawQuery("SELECT * FROM notlar",null);
        while (c.moveToNext()){

            Notlar not=new Notlar(c.getInt(c.getColumnIndex("notId"))
            ,c.getString(c.getColumnIndex("dersAdi"))
            ,c.getInt(c.getColumnIndex("notVize"))
            ,c.getInt(c.getColumnIndex("notFinal")));

            notlarArrayList.add(not);
        }

        db.close();
        return notlarArrayList;

    }

    public void notSil(DbHelper dbHelper,int notId){

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("notlar","notID=?",new String[] {String.valueOf(notId)});
        db.close();

    }
    public void notEkle(DbHelper dbHelper,String dersAdi,int notVize,int notFinal){

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dersAdi",dersAdi);
        values.put("notVize",notVize);
        values.put("notFinal",notFinal);

        db.insertOrThrow("notlar",null,values);
        db.close();


    }
    public void notDuzenle(DbHelper dbHelper,int notId,String dersAdi,int notVize,int notFinal){

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("dersAdi",dersAdi);
        values.put("notVize",notVize);
        values.put("notFinal",notFinal);

        db.update("notlar",values,"notId=?",new String[]{String.valueOf(notId)});
        db.close();


    }
}
