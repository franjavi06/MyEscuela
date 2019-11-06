package com.example.myescuela;

import android.content.Intent;
import android.os.Bundle;

import com.example.myescuela.entidad.Estudiante;
import com.example.myescuela.repositorio.EstudianteRepositorioDbImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Adaptador.OnEstudianteListener {

    EstudianteRepositorioDbImpl estudianteRepositorio;

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Estudiante>  estudiantes;
    Adaptador.OnEstudianteListener onEstudianteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        //lv = findViewById(R.id.lvestudiantes);
        rv = findViewById(R.id.rvestudiantes);
        onEstudianteListener = this;

        rv.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        rv.addItemDecoration(decoration);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEstudiante = new Intent(view.getContext(),AddEstudiante.class);
                startActivity(addEstudiante);
            }
        });


        estudiantes = estudianteRepositorio.buscar();

        if (estudiantes.size()>0){
            //lv.setAdapter(new Adaptador(contexto, estudiantes));
            //rv.setAdapter(new Adaptador(contexto, estudiantes));
            // specify an adapter (see also next example)
            mAdapter = new Adaptador(estudiantes,onEstudianteListener);
            rv.setAdapter(mAdapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay estudiantes registrados", Toast.LENGTH_SHORT).show();
        }

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalleEstudiante = new Intent(view.getContext(), DetalleEstudiante.class);
                detalleEstudiante.putExtra("ID", estudiantes.get(position).getId().toString());
                detalleEstudiante.putExtra("NOMBRE", estudiantes.get(position).getNombre());
                detalleEstudiante.putExtra("MATRICULA", estudiantes.get(position).getMatricula());
                startActivity(detalleEstudiante);
            }
        });*/

        /*rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalleEstudiante = new Intent(view.getContext(), DetalleEstudiante.class);
                detalleEstudiante.putExtra("ID", estudiantes.get(position).getId().toString());
                detalleEstudiante.putExtra("NOMBRE", estudiantes.get(position).getNombre());
                detalleEstudiante.putExtra("MATRICULA", estudiantes.get(position).getMatricula());
                startActivity(detalleEstudiante);
            }
        });*/
    }

    @Override
    public void onEstudianteClick(int position){
        Intent detalleEstudiante = new Intent(this, DetalleEstudiante.class);
        detalleEstudiante.putExtra("ID", estudiantes.get(position).getId().toString());
        detalleEstudiante.putExtra("NOMBRE", estudiantes.get(position).getNombre());
        detalleEstudiante.putExtra("MATRICULA", estudiantes.get(position).getMatricula());
        detalleEstudiante.putExtra("CARRERA_ID", estudiantes.get(position).getCarrera().getId().toString());
        detalleEstudiante.putExtra("CARRERA_NOMBRE", estudiantes.get(position).getCarrera().getNombre());
        startActivity(detalleEstudiante);
    }

    @Override
    protected void onResume() {

        super.onResume();
        estudiantes = estudianteRepositorio.buscar();

        if (estudiantes.size()>0){
            //lv.setAdapter(new Adaptador(this, estudiantes));
            mAdapter = new Adaptador(estudiantes,this);
            rv.setAdapter(mAdapter);
        }
        else {
            Toast.makeText(getApplicationContext(), "No hay estudiantes registrados", Toast.LENGTH_SHORT).show();
        }
    }


}
