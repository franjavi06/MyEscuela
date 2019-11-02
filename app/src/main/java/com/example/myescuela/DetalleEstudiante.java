package com.example.myescuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Estudiante;
import com.example.myescuela.repositorio.CarreraRepositorioDbImpl;
import com.example.myescuela.repositorio.EstudianteRepositorioDbImpl;

import java.util.List;

public class DetalleEstudiante extends AppCompatActivity {

    TextView id;
    TextView nombre;
    TextView matricula;
    TextView carreraNombre;
    EditText nombreE;
    EditText matriculaE;
    Button editar;
    Button guardar;
    Button cancelar;
    Button cancelar1;
    EstudianteRepositorioDbImpl estudianteRepositorio;
    CarreraRepositorioDbImpl carreraRepositorio;
    Button acarrerabtn;
    List<Carrera> carreras;
    Spinner dropdown_carreras;
    ArrayAdapter<Carrera> adapter;
    Integer idcarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_estudiante);

        id = findViewById(R.id.textViewId_Detalle);
        nombre = findViewById(R.id.textViewNombre_Detalle);
        matricula = findViewById(R.id.textViewMatricula_Detalle);
        carreraNombre = findViewById(R.id.textViewCarreraNombre_Detalle);
        nombreE = findViewById(R.id.editTextNombre_Detalle);
        matriculaE = findViewById(R.id.editTextMatricula_Detalle);
        editar = findViewById(R.id.buttonEditar_Detalle);
        guardar = findViewById(R.id.buttonGuardar_Detalle);
        cancelar = findViewById(R.id.buttonCancelar_Detalle);
        cancelar1 = findViewById(R.id.buttonCancelar_Detalle1);
        acarrerabtn = findViewById(R.id.button_a_Carreras_detalleEstudiante);
        dropdown_carreras = findViewById(R.id.carreras_select_detalleEstudiante);
        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());
        carreraRepositorio = new CarreraRepositorioDbImpl(this.getBaseContext());
        idcarrera = 0;

        carreras = carreraRepositorio.buscar();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,carreras);
        //set the spinners adapter to the previously created one.
        dropdown_carreras.setAdapter(adapter);

        nombreE.setVisibility(View.GONE);
        matriculaE.setVisibility(View.GONE);
        guardar.setVisibility(View.GONE);
        cancelar.setVisibility(View.GONE);
        acarrerabtn.setVisibility(View.GONE);
        dropdown_carreras.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            id.setText(b.getString("ID"));
            nombre.setText(b.getString("NOMBRE"));
            matricula.setText(b.getString("MATRICULA"));
            carreraNombre.setText(b.getString("CARRERA_NOMBRE"));
            idcarrera = Integer.parseInt(b.getString("CARRERA_ID"));
        }

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreE.setVisibility(View.VISIBLE);
                matriculaE.setVisibility(View.VISIBLE);
                guardar.setVisibility(View.VISIBLE);
                cancelar.setVisibility(View.VISIBLE);
                acarrerabtn.setVisibility(View.VISIBLE);

                nombreE.setText(nombre.getText());
                matriculaE.setText(matricula.getText());
                nombre.setVisibility(View.GONE);
                matricula.setVisibility(View.GONE);
                carreraNombre.setVisibility(View.GONE);
                editar.setVisibility(View.GONE);
                cancelar1.setVisibility(View.GONE);
                Integer poscarrera = 0;
                for (int i = 0; i < carreras.size(); i++) {
                    if (carreras.get(i).getId() == idcarrera){
                        poscarrera = i;
                        break;
                    }
                }
                dropdown_carreras.setSelection(poscarrera);
                //Toast.makeText(getApplicationContext(), idcarrera.toString()+" "+poscarrera.toString(), Toast.LENGTH_SHORT).show();
                dropdown_carreras.setVisibility(View.VISIBLE);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreE.setVisibility(View.GONE);
                matriculaE.setVisibility(View.GONE);
                guardar.setVisibility(View.GONE);
                cancelar.setVisibility(View.GONE);
                acarrerabtn.setVisibility(View.GONE);
                dropdown_carreras.setVisibility(View.GONE);
                nombre.setVisibility(View.VISIBLE);
                matricula.setVisibility(View.VISIBLE);
                carreraNombre.setVisibility(View.VISIBLE);
                editar.setVisibility(View.VISIBLE);
                cancelar1.setVisibility(View.VISIBLE);
            }
        });

        cancelar1.setOnClickListener(new View.OnClickListener() {
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

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Se editará el estudiante con el id: "+id.getText()+"Con Nombre="+nombreE.getText()+" y Matricula="+matriculaE.getText(), Toast.LENGTH_SHORT).show();

                if (nombreE.getText().toString().length() == 0 ||  matriculaE.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "No se aceptan campos vacíos", Toast.LENGTH_SHORT).show();
                }
                else{
                    String mensaje = "";
                    Carrera cs = (Carrera) dropdown_carreras.getSelectedItem();
                    Estudiante est1 = new Estudiante(Integer.parseInt(id.getText().toString()),nombreE.getText().toString(),matriculaE.getText().toString(),new Carrera(cs.getId(),cs.getNombre()));
                    long respuesta = estudianteRepositorio.actualizar(est1);
                    if (respuesta==-1)
                        mensaje = "Error al Actualizar Estudiante";
                    else
                    {
                        mensaje = "Estudiante Actualizado! Cantidad Registros Afectados: "+respuesta;
                        nombreE.setVisibility(View.GONE);
                        matriculaE.setVisibility(View.GONE);
                        guardar.setVisibility(View.GONE);
                        cancelar.setVisibility(View.GONE);
                        acarrerabtn.setVisibility(View.GONE);
                        dropdown_carreras.setVisibility(View.GONE);
                        nombre.setVisibility(View.VISIBLE);
                        matricula.setVisibility(View.VISIBLE);
                        carreraNombre.setVisibility(View.VISIBLE);
                        editar.setVisibility(View.VISIBLE);
                        cancelar1.setVisibility(View.VISIBLE);

                        nombre.setText(nombreE.getText());
                        matricula.setText(matriculaE.getText());

                    }

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }

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
