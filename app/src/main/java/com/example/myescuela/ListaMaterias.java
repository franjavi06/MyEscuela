package com.example.myescuela;

import android.content.Intent;
import android.os.Bundle;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Materia;
import com.example.myescuela.repositorio.MateriaRepositorioDbImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ListaMaterias extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    MateriaRepositorioDbImpl materiaRepositorio;
    List<Materia> materias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_materias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.rvmaterias);

        rv.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        rv.addItemDecoration(decoration);

        materiaRepositorio = new MateriaRepositorioDbImpl(this.getBaseContext());

        materias = materiaRepositorio.buscar();

        if (materias.size()>0){
            //lv.setAdapter(new Adaptador(contexto, estudiantes));
            //rv.setAdapter(new Adaptador(contexto, estudiantes));
            // specify an adapter (see also next example)
            mAdapter = new MateriaAdaptador(materias);
            rv.setAdapter(mAdapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay estudiantes registrados", Toast.LENGTH_SHORT).show();
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMateria = new Intent(view.getContext(),AddMateria.class);
                startActivity(addMateria);
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        materias = materiaRepositorio.buscar();

        if (materias.size()>0){
            mAdapter = new MateriaAdaptador(materias);
            rv.setAdapter(mAdapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay materias registradas", Toast.LENGTH_SHORT).show();
        }
    }

}
