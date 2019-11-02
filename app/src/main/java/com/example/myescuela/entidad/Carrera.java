package com.example.myescuela.entidad;

import com.example.myescuela.ListaCarreras;

import java.util.List;

public class Carrera {

    //campos de la tabla carrera
    private Integer id;
    private String nombre;
    private List<Materia> materias;

    //campos extra
    //private Integer cantmaterias;
    //private Integer cantcreditos;

    //usado al mostrar carreras
    public Carrera(Integer id, String nombre, List<Materia> materias) {
        this.id = id;
        this.nombre = nombre;
        this.materias = materias;
    }

    //usado al crear estudiantes
    public Carrera(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.materias = materias;
    }

    //usado al crear carrera
    public Carrera(String nombre, List<Materia> materias) {
        this.id = id;
        this.nombre = nombre;
        this.materias = materias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public int getCantMaterias() {
        return materias.size();
    }

    public int getCantCreditos() {
        int creditos = 0;
        for (int i = 0; i < materias.size(); i++) {
            creditos += materias.get(i).getCreditos();
        }
        return creditos;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
