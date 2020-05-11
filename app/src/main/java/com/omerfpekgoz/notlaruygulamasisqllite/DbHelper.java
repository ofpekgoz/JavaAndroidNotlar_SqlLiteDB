package com.omerfpekgoz.notlaruygulamasisqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(@Nullable Context context) {
        super(context, "notlar.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"notlar\" (\n" +
                "\t\"notId\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"dersAdi\"\tTEXT,\n" +
                "\t\"notVize\"\tINTEGER,\n" +
                "\t\"notFinal\"\tINTEGER\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notlar");
        onCreate(db);

    }
}
