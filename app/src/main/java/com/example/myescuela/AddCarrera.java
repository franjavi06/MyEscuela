package com.example.myescuela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Materia;
import com.example.myescuela.repositorio.CarreraRepositorioDbImpl;
import com.example.myescuela.repositorio.MateriaRepositorioDbImpl;

import java.util.ArrayList;
import java.util.List;

public class AddCarrera extends AppCompatActivity implements MateriaAdaptadorC.OnMateriaListener{

    MateriaRepositorioDbImpl materiaRepositorio;
    CarreraRepositorioDbImpl carreraRepositorio;
    List<Materia> materias;
    Spinner dropdown_materias;
    Button amateriabtn;
    RecyclerView rvmaterias_sel;
    List<Materia> materias_seleccionadas;
    Button addmateriabtn;
    Button remmateriabtn;
    Button btncancelar;
    Button btnguardar;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    MateriaAdaptadorC.OnMateriaListener onMateriaListener;
    int rviseleccionado;
    EditText nombrecarrera;
    ArrayAdapter<Materia> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carrera);

        dropdown_materias = findViewById(R.id.materias_select_AddCarrera);
        materiaRepositorio = new MateriaRepositorioDbImpl(this.getBaseContext());
        carreraRepositorio = new CarreraRepositorioDbImpl(this.getBaseContext());
        amateriabtn = findViewById(R.id.button_a_Materias_AddCarrera);
        rvmaterias_sel = findViewById(R.id.rvmaterias_AddCarreras);
        addmateriabtn = findViewById(R.id.AddMateria_Add_Carreras);
        remmateriabtn = findViewById(R.id.RemMateria_Add_Carreras);
        btncancelar = findViewById(R.id.buttonCancelar_AddCarrera);
        btnguardar = findViewById(R.id.buttonGuardar_AddCarrera);
        nombrecarrera = findViewById(R.id.editTextNombreCarrera_AddCarrera);
        materias_seleccionadas = new ArrayList<Materia>();
        onMateriaListener = this;
        rviseleccionado = 0;

        rvmaterias_sel.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvmaterias_sel.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        rvmaterias_sel.addItemDecoration(decoration);

        materias = materiaRepositorio.buscar();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, materias);
        dropdown_materias.setAdapter(adapter);

        amateriabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMateria = new Intent(v.getContext(),ListaMaterias.class);
                startActivity(aMateria);
            }
        });

        addmateriabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Materia ms = (Materia) dropdown_materias.getSelectedItem();

                if(materias_seleccionadas.contains(ms)){
                    Toast.makeText(getApplicationContext(), "El elemento ya está en la lista", Toast.LENGTH_SHORT).show();
                }
                else{
                    materias_seleccionadas.add(ms);
                    Toast.makeText(getApplicationContext(), "Se añadió "+ ms.getNombre(), Toast.LENGTH_SHORT).show();
                    rviseleccionado = 0;
                    mAdapter = new MateriaAdaptadorC(materias_seleccionadas,onMateriaListener,rviseleccionado);
                    rvmaterias_sel.setAdapter(mAdapter);
                }

            }
        });

        remmateriabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materias_seleccionadas.size()>0){
                    Materia eliminada = materias_seleccionadas.remove(rviseleccionado);
                    Toast.makeText(getApplicationContext(), "Se removió "+ eliminada.getNombre(), Toast.LENGTH_SHORT).show();
                    rviseleccionado = 0;
                    mAdapter = new MateriaAdaptadorC(materias_seleccionadas,onMateriaListener,rviseleccionado);
                    rvmaterias_sel.setAdapter(mAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "La lista está vacia!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombrecarrera.getText().toString().length() == 0 ||  materias_seleccionadas.size() == 0){
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    String mensaje = "";
                    //Toast.makeText(getApplicationContext(), cs.getId().toString() , Toast.LENGTH_SHORT).show();
                    long respuesta = carreraRepositorio.crear(new Carrera(nombrecarrera.getText().toString(),materias_seleccionadas));
                    if (respuesta==-1)
                        mensaje = "Error al Crear Carrera";
                    else
                    {
                        mensaje = "Carrera Creada! Id: "+respuesta;

                    }
                    Toast.makeText(getApplicationContext(), mensaje , Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        materias = materiaRepositorio.buscar();

        if (materias.size()>0){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,materias);
            dropdown_materias.setAdapter(adapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay materias registradas", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMateriaClick(int position) {

        rviseleccionado = position;
        mAdapter = new MateriaAdaptadorC(materias_seleccionadas,onMateriaListener,position);
        rvmaterias_sel.setAdapter(mAdapter);

    }
}
