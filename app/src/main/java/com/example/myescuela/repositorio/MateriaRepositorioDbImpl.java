package com.example.myescuela.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Materia;

import java.util.ArrayList;
import java.util.List;

public class MateriaRepositorioDbImpl implements MateriaRepositorio{

    private static final String  TABLE = "estudiante";
    private DbConexion dbConexion;

    public MateriaRepositorioDbImpl(Context context){

        this.dbConexion = new DbConexion(context);
    }

    @Override
    public long crear(Materia materia) {

        ContentValues cv = new ContentValues();
        cv.put("nombre", materia.getNombre());
        cv.put("creditos", materia.getCreditos());

        SQLiteDatabase db = dbConexion.getWritableDatabase();
        long id = db.insert("materia",null,cv);

        if (id <= 0){
            Log.i("MateriaRepositorio","Error al crear materia");
        }else {
            Log.i("MateriaRepositorio","La materia se ha creado exitosamente - "+"id: "+id);
        }

        return id;
    }

    @Override
    public long actualizar(Materia materia) {
        return 0;
    }

    @Override
    public void borrar(Materia materia) {

    }

    @Override
    public Materia buscar(int id) {
        return null;
    }

    @Override
    public List<Materia> buscar() {
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from materia;",null);
        List<Materia> materias = new ArrayList<Materia>();

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                Integer creditos = cursor.getInt(cursor.getColumnIndex("creditos"));
                materias.add(new Materia(id,nombre,creditos));
            } while(cursor.moveToNext());
        }

        cursor.close();

        return materias;
    }

}
