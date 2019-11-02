package com.example.myescuela;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Estudiante;
import com.example.myescuela.repositorio.CarreraRepositorioDbImpl;
import com.example.myescuela.repositorio.EstudianteRepositorioDbImpl;

import java.util.List;

public class AddEstudiante extends AppCompatActivity {

    EditText nombre;
    EditText matricula;
    Button btnguardar;
    Button btncancelar;
    Button acarrerabtn;
    EstudianteRepositorioDbImpl estudianteRepositorio;
    CarreraRepositorioDbImpl carreraRepositorio;
    List<Carrera> carreras;
    Spinner dropdown_carreras;
    ArrayAdapter<Carrera> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estudiante);

        nombre = findViewById(R.id.editTextNombre);
        matricula = findViewById(R.id.editTextMatricula);
        btnguardar = findViewById(R.id.buttonGuardar_Add);
        btncancelar = findViewById(R.id.buttonCancelar_Add);
        acarrerabtn = findViewById(R.id.button_a_Carreras);
        dropdown_carreras = findViewById(R.id.carreras_select_addEstudiante);
        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());
        carreraRepositorio = new CarreraRepositorioDbImpl(this.getBaseContext());


        carreras = carreraRepositorio.buscar();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,carreras);
        //set the spinners adapter to the previously created one.
        dropdown_carreras.setAdapter(adapter);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombre.getText().toString().length() == 0 ||  matricula.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Carrera cs = (Carrera) dropdown_carreras.getSelectedItem();
                    String mensaje = "";
                    //Toast.makeText(getApplicationContext(), cs.getId().toString() , Toast.LENGTH_SHORT).show();
                    long respuesta = estudianteRepositorio.crear(new Estudiante(nombre.getText().toString(),matricula.getText().toString(),new Carrera(cs.getId(),cs.getNombre())));
                    if (respuesta==-1)
                        mensaje = "Error al Crear Estudiante";
                    else
                    {
                        mensaje = "Estudiante Creado! Id: "+respuesta;

                    }

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        acarrerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aCarrera = new Intent(v.getContext(),ListaCarreras.class);
                startActivity(aCarrera);
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        carreras = carreraRepositorio.buscar();

        if (carreras.size()>0){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,carreras);
            dropdown_carreras.setAdapter(adapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay carreras registradas", Toast.LENGTH_SHORT).show();
        }
    }


}
