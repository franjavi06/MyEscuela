package com.example.myescuela.repositorio;

import com.example.myescuela.entidad.Estudiante;

import java.util.List;

public interface EstudianteRepositorio {

    void crear(Estudiante estudiante);
    void actualizar(Estudiante estudiante);
    void borrar(Estudiante estudiante);
    Estudiante buscar(int id);
    List<Estudiante> buscar();

}
