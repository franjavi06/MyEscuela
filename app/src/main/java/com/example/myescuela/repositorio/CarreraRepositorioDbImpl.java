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

public class CarreraRepositorioDbImpl implements CarreraRepositorio{

    private static final String  TABLE = "estudiante";
    private DbConexion dbConexion;

    public CarreraRepositorioDbImpl(Context context){
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public long crear(Carrera carrera) {

        ContentValues cv = new ContentValues();
        cv.put("nombre", carrera.getNombre());

        SQLiteDatabase db = dbConexion.getWritableDatabase();
        long id = db.insert("carrera",null,cv);

        if (id <= 0){
            Log.i("CarreraRepositorio","Error al crear carrera");
        }else {
            Log.i("CarreraRepositorio","La carrera se ha creado exitosamente - "+"id: "+id);
            setMateriasByIdCarrera(id,carrera.getMaterias());
        }

        return id;

    }

    private void setMateriasByIdCarrera(long idcarrera, List<Materia> materias){

        for (int i = 0; i < materias.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("carrera_id", idcarrera);
            cv.put("materia_id", materias.get(i).getId());

            SQLiteDatabase db = dbConexion.getWritableDatabase();
            long id = db.insert("carrera_materia",null,cv);
            if (id <= 0){
                Log.i("CarreraRepositorio","Error al agregar materia");
            }else {
                Log.i("CarreraRepositorio","La materia se ha agregado exitosamente - "+"id: "+id);
            }
        }

    }

    @Override
    public long actualizar(Carrera carrera) {
        return 0;
    }

    @Override
    public void borrar(Carrera carrera) {

    }

    @Override
    public Carrera buscar(int id) {
        return null;
    }

    @Override
    public List<Carrera> buscar() {
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id, nombre from carrera;",null);
        List<Carrera> carreras = new ArrayList<Carrera>();

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                carreras.add(new Carrera(id,nombre,getMateriasByIdCarrera(id)));
            } while(cursor.moveToNext());
        }

        cursor.close();

        return carreras;
    }

    private List<Materia> getMateriasByIdCarrera(Integer idcarrera){
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT m.id, m.nombre, m.creditos, cm.carrera_id\n" +
                "FROM materia m\n" +
                "INNER JOIN carrera_materia cm on m.id = cm.materia_id\n" +
                "WHERE carrera_id = "+idcarrera+";",null);
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
