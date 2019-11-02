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

        db.execSQL("CREATE TABLE \"carrera\" (\"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\"nombre\" TEXT NOT NULL);");
        db.execSQL("CREATE TABLE \"materia\" (\"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT NOT NULL, \"creditos\" INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE \"carrera_materia\" (\"carrera_id\" INTEGER NOT NULL, \"materia_id\" INTEGER NOT NULL, PRIMARY KEY(\"carrera_id\",\"materia_id\"), FOREIGN KEY(\"carrera_id\") REFERENCES \"carrera\", FOREIGN KEY(\"materia_id\") REFERENCES \"materia\" );");
        db.execSQL("CREATE TABLE \"estudiante\" (\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT NOT NULL, \"matricula\"\tTEXT NOT NULL, \"carrera_id\" INTEGER, FOREIGN KEY(\"carrera_id\") REFERENCES \"carrera\");");
        // db.execSQL("CREATE TABLE \"estudiante\" (\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\"nombre\" TEXT NOT NULL, \"matricula\"\tTEXT NOT NULL);");
        db.execSQL("INSERT INTO \"carrera\" (\"nombre\") VALUES ('Ingenieria Telematica');");
        db.execSQL("INSERT INTO \"carrera\" (\"nombre\") VALUES ('Ingenieria en Sistemas');");
        db.execSQL("INSERT INTO \"carrera\" (\"nombre\") VALUES ('Administracion Hotelera');");
        db.execSQL("INSERT INTO \"carrera\" (\"nombre\") VALUES ('Mercadeo');");
        db.execSQL("INSERT INTO \"carrera\" (\"nombre\") VALUES ('Arquitectura');");
        db.execSQL("INSERT INTO \"materia\" (\"nombre\", \"creditos\") VALUES ('Programacion 1', '5');");
        db.execSQL("INSERT INTO \"materia\" (\"nombre\", \"creditos\") VALUES ('Matematicas 1', '4');");
        db.execSQL("INSERT INTO \"materia\" (\"nombre\", \"creditos\") VALUES ('Espanol 1', '3');");
        db.execSQL("INSERT INTO \"materia\" (\"nombre\", \"creditos\") VALUES ('Tecnologias de Transmision', '3');");
        db.execSQL("INSERT INTO \"materia\" (\"nombre\", \"creditos\") VALUES ('Bases de Datos 1', '4');");
        db.execSQL("INSERT INTO \"materia\" (\"nombre\", \"creditos\") VALUES ('Bases de Datos 2', '4');");
        db.execSQL("INSERT INTO \"estudiante\" (\"nombre\", \"matricula\", \"carrera_id\") VALUES ('Francisco Tejada', '2011-0122', '1');");
        db.execSQL("INSERT INTO \"estudiante\" (\"nombre\", \"matricula\", \"carrera_id\") VALUES ('Fernando Valencia', '2012-1234', '2');");
        db.execSQL("INSERT INTO \"estudiante\" (\"nombre\", \"matricula\", \"carrera_id\") VALUES ('Jenifer Rodriguez', '2009-4321', '3');");
        db.execSQL("INSERT INTO \"estudiante\" (\"nombre\", \"matricula\", \"carrera_id\") VALUES ('Juan Camilo', '2013-6473', '1');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('1', '1');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('1', '4');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('2', '1');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('2', '5');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('2', '6');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('3', '2');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('3', '3');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('4', '2');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('4', '3');");
        db.execSQL("INSERT INTO \"carrera_materia\" (\"carrera_id\", \"materia_id\") VALUES ('5', '3');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
