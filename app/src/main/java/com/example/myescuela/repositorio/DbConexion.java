package com.example.myescuela.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbConexion extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private final static String NAME_DB = "escuela.db";
    public DbConexion(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE \"estudiante\" (\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\"nombre\"INTEGER NOT NULL,\"matricula\"\tINTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
