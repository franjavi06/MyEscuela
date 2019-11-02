package com.example.myescuela.repositorio;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Estudiante;

import java.util.List;

public interface CarreraRepositorio {

    long crear(Carrera carrera);
    long actualizar(Carrera carrera);
    void borrar(Carrera carrera);
    Carrera buscar(int id);
    List<Carrera> buscar();

}
