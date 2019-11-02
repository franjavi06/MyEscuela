package com.example.myescuela.entidad;

public class Estudiante {

    //campos de la tabla estudiante
    private Integer id;
    private String nombre;
    private String matricula;
    private Carrera carrera;
    /*private Integer carrera_id;

    //campos extra
    private String carrera_nombre;*/

    public Estudiante(){

    }

    //Constructor utilizado en buscar() y actualizar
    public Estudiante(Integer id, String nombre, String matricula, Carrera carrera){
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.carrera = carrera;
    }

    //Constructor utilizado en crear()
    public Estudiante(String nombre, String matricula, Carrera carrera){
        this.nombre = nombre;
        this.matricula = matricula;
        this.carrera = carrera;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
