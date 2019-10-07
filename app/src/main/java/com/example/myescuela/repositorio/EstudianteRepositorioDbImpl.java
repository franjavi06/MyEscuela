package com.example.myescuela.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myescuela.entidad.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositorioDbImpl implements EstudianteRepositorio{

    private DbConexion dbConexion;

    public EstudianteRepositorioDbImpl(Context context){
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public void crear(Estudiante estudiante) {

        ContentValues cv = new ContentValues();
        cv.put("nombre", estudiante.getNombre());
        cv.put("matricula", estudiante.getMatricula());

        SQLiteDatabase db = dbConexion.getWritableDatabase();
        long id = db.insert("estudiante",null,cv);

        if (id <= 0){
            Log.i("EstudianteRepositorio","Error al crear estudiante");
        }else {
            Log.i("EstudianteRepositorio","El estudiante se ha creado exitosamente - "+"id: "+id);
        }

    }

    @Override
    public void actualizar(Estudiante estudiante) {

    }

    @Override
    public void borrar(Estudiante estudiante) {

    }

    @Override
    public Estudiante buscar(int id) {
        return null;
    }

    @Override
    public List<Estudiante> buscar() {

        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from estudiante;",null);
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();

        return estudiantes;
    }
}
