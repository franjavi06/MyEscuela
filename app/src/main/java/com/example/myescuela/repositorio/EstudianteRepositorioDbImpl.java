package com.example.myescuela.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositorioDbImpl implements EstudianteRepositorio{

    private static final String  TABLE = "estudiante";
    private DbConexion dbConexion;

    public EstudianteRepositorioDbImpl(Context context){
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public long crear(Estudiante estudiante) {

        ContentValues cv = new ContentValues();
        cv.put("nombre", estudiante.getNombre());
        cv.put("matricula", estudiante.getMatricula());
        cv.put("carrera_id", estudiante.getCarrera().getId());

        SQLiteDatabase db = dbConexion.getWritableDatabase();
        long id = db.insert("estudiante",null,cv);

        if (id <= 0){
            Log.i("EstudianteRepositorio","Error al crear estudiante");
        }else {
            Log.i("EstudianteRepositorio","El estudiante se ha creado exitosamente - "+"id: "+id);
        }

        return id;
    }

    @Override
    public long actualizar(Estudiante estudiante) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", estudiante.getNombre());
        cv.put("matricula", estudiante.getMatricula());
        cv.put("carrera_id", estudiante.getCarrera().getId());

        SQLiteDatabase db = dbConexion.getWritableDatabase();
        long resultado = db.update("estudiante",cv,"id="+estudiante.getId(),null);

        if (resultado <= 0){
            Log.i("EstudianteRepositorio","Error al actualizar estudiante");
        }else {
            Log.i("EstudianteRepositorio","Fueron Actualizados - "+resultado+" registros");
        }

        return resultado;

    }

    @Override
    public void borrar(Estudiante estudiante) {

    }

    @Override
    public Estudiante buscar(int id) {

        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select e.id, e.nombre, e.matricula, e.carrera_id, c.nombre as carrera_nombre from estudiante e inner join carrera c on e.carrera_id = c.id where id = "+id+";",null);
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Integer idc = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String matricula = cursor.getString(cursor.getColumnIndex("matricula"));
                Integer carrera_id = cursor.getInt(cursor.getColumnIndex("carrera_id"));
                String carrera_nombre = cursor.getString(cursor.getColumnIndex("carrera_nombre"));
                estudiantes.add(new Estudiante(idc,nombre,matricula,new Carrera(carrera_id,carrera_nombre)));
            } while(cursor.moveToNext());
        }

        cursor.close();

        return estudiantes.get(0);
    }

    @Override
    public List<Estudiante> buscar() {

        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select e.id, e.nombre, e.matricula, e.carrera_id, c.nombre as carrera_nombre from estudiante e inner join carrera c on e.carrera_id = c.id;",null);
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String matricula = cursor.getString(cursor.getColumnIndex("matricula"));
                Integer carrera_id = cursor.getInt(cursor.getColumnIndex("carrera_id"));
                String carrera_nombre = cursor.getString(cursor.getColumnIndex("carrera_nombre"));
                estudiantes.add(new Estudiante(id,nombre,matricula,new Carrera(carrera_id,carrera_nombre)));
            } while(cursor.moveToNext());
        }

        cursor.close();

        return estudiantes;
    }
}
