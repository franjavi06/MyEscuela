package com.example.myescuela.repositorio;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Materia;

import java.util.List;

public interface MateriaRepositorio {

    long crear(Materia materia);
    long actualizar(Materia materia);
    void borrar(Materia materia);
    Materia buscar(int id);
    List<Materia> buscar();

}
